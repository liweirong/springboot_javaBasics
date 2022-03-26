package svip_homework.n_sentinel;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

/**
 * @author iris
 * @date 2022/3/17
 */
@Service
public class OrderService {
    /**
     * 订单查询接口, 使用Sentinel注解实现限流
     *SentinelResource 配置的资源名
     * blockHandler 限流之后执行
     * fallback 熔断执行
     * @param orderId
     * @return
     */
    @SentinelResource(value = "queryOrderInfoResourceName",
            blockHandler = "handleFlowQpsException",
            fallback = "queryOrderInfo3Fallback")
    public String queryOrderInfo(String orderId) {
// 模拟接口运行时抛出代码异常
        if ("000".equals(orderId)) {
            throw new RuntimeException();
        }
        System.out.println("获取订单信息:" + orderId);
        return "return OrderInfo3 :" + orderId;
    }

    /**
     * 订单查询接口抛出限流或降级时的处理逻辑
     * <p>
     * 注意: 方法参数、返回值要与原函数保持一致
     *
     * @return
     */
    public String handleFlowQpsException(String orderId, BlockException e) {
        e.printStackTrace();
        return "订单查询接口抛出限流或降级时的处理逻辑 for queryOrderInfo3: " + orderId;
    }

    /**
     * 订单查询接口运行时抛出的异常提供fallback处理
     * <p>
     * 注意: 方法参数、返回值要与原函数保持一致
     *
     * @return
     */
    public String queryOrderInfo3Fallback(String orderId, Throwable e) {
        e.printStackTrace();
        return "订单查询接口运行时抛出的异常提供fallback处理 queryOrderInfo3: " + orderId;
    }
}
