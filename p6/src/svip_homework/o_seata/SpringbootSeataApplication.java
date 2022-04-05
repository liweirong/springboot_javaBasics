package svip_homework.o_seata;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import svip_homework.n_sentinel.OrderService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iris
 * @date 2022/4/5
 * 用户购买商品的业务逻辑。整个业务逻辑由3个微服务提供支持：
 *
 * 仓储服务：对给定的商品扣除仓储数量。
 * 订单服务：根据采购需求创建订单。
 * 帐户服务：从用户帐户中扣除余额。
 */
@SpringBootApplication
@RestController
@Slf4j
public class SpringbootSeataApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootSeataApplication.class, args);
    }




}
