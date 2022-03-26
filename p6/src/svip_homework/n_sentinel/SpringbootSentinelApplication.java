package svip_homework.n_sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iris
 * @date 2022/3/17
 */
@SpringBootApplication
@RestController
@Slf4j
public class SpringbootSentinelApplication {
    public static void main(String[] args) {
        initFlowQpsRule();
        SpringApplication.run(SpringbootSentinelApplication.class, args);
    }

    /**
     * 基础核心api 一定要try catch来捕获
     */
    public static void initFlowQpsRule() {
        log.info("初始化限流规则");
        List<FlowRule> rules = new ArrayList<FlowRule>();
        FlowRule rule1 = new FlowRule();
        // 1 设置资源名 方法限流
        rule1.setResource("queryOrderInfoResourceName");
        // QPS控制在2以内
        rule1.setCount(2);
        // QPS限流
        rule1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // 业务系统限流
        rule1.setLimitApp("default");
        rules.add(rule1);
        // 规则设置
        FlowRuleManager.loadRules(rules);
    }

    @Autowired
    OrderService orderService;

    /**
     * localhost:8081/testSentinel
     * @return
     */
    @GetMapping("testSentinel")
    public Object test() {
        Entry entry = null;
        try {
            /**
             * 资源名
             */
            entry = SphU.entry("queryOrderInfoResourceName");
//            return orderService.queryOrderInfo("001");
            // 熔断
            return orderService.queryOrderInfo("000");
        } catch (BlockException e) {
            return "接口限流返回空";
        }finally {
            if (entry != null) {
                entry.exit();
            }
        }
    }


}
