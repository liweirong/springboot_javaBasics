package drools;


import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iris
 * @date 2023/5/25
 */
@RestController
@SpringBootApplication
public class DroolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DroolsApplication.class);


    }

    @Autowired
    private KieBase kieBase;

    /**
     * 应纳税所得额=月度收入-5000元(免征额)-专项扣除(三险一金等)-专项附加扣除-依法确定的其他扣除
     * <p>
     * 1级：不超过3.6万元的，3%
     * 2级：超过3.6万元至14.4万元的部分，10%
     * 3级：超过14.4万元至30万元的部分，20%
     * 4级：超过30万元至42万元的部分，25%
     * 5级：超过42万元至66万元的部分，30%
     * 6级：超过66万元至96万元的部分，35%
     * 7级：超过96万元的部分，45%
     *
     * @param base
     * @return
     */
    @GetMapping("/drools/{base}")
    public Map<Integer, Double> getResult(@PathVariable("base") Double base) {
        Map<Integer, Double> resultMap = new HashMap<>();
        double total = (double) 0;
        for (int i = 1; i <= 12; i++) {
            Calculation calculation = new Calculation();
            calculation.setWage(base);
            KieSession session = kieBase.newKieSession();   //获得session
            session.insert(calculation);    //加入到规则内存
            session.fireAllRules();         //启动所有规则
            session.dispose();              //销毁session
            //返回经历过规则的calculation
            resultMap.put(i, calculation.getActualWage());
            total += calculation.getActualWage();
        }
        return resultMap;
    }

    @GetMapping("/rule/calculate")
    public Calculation calculation(Double wage) {
        Calculation calculation = new Calculation();
        calculation.setWage(wage);
        KieSession session = kieBase.newKieSession();   //获得session
        session.insert(calculation);    //加入到规则内存
        session.fireAllRules();         //启动所有规则
        session.dispose();              //销毁session
        System.out.println(calculation);
        return calculation;
    }
}
