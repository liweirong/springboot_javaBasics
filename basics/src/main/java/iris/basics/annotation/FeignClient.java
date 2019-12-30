package iris.basics.annotation;

import java.lang.annotation.*;

/**
 * @author iris
 * @date 2019/12/26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FeignClient {
}
