package svip_homework.design.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author iris
 * @data 2021/10/12
 * <p>
 * cglb 是因为cglib会为每一个方法生成一个index的索引，找方法的时候直接通过这个索性定位到方法
 */
public class CGLIBProxy {

    public static void main(String[] args) {

        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "C:\\Users\\huawei\\Desktop");
        CGLIBProxyBean cglbProxyBean = new CGLIBProxyBean();
        IStudy study = cglbProxyBean.getInstance(AloneStudy.class);
        study.learn();

        // 如果被代理类被final关键字所修饰，那么抱歉会失败
        // Exception in thread "main" java.lang.IllegalArgumentException: Cannot subclass final class svip_homework.design.proxy.TestFinalClass
        // cglbProxyBean.getInstance(TestFinalClass.class);
    }

}

final class TestFinalClass {

}

class CGLIBProxyBean implements MethodInterceptor {

    public <T> T getInstance(Class<? extends T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(this);
        enhancer.setSuperclass(clazz);
        return (T) enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object result = methodProxy.invokeSuper(o, objects);
        after();
        return result;
    }

    private void before() {
        System.out.println("CGLB代理前  - 干点什么");
    }

    private void after() {
        System.out.println("CGLB代理后  - 干点什么!");
    }
}
