package com.iris.rpc;

import com.iris.rpc.annotion.MyRemoteService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class InitialMerdiator implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
       if(bean.getClass().isAnnotationPresent(MyRemoteService.class)){
           Method[] declaredMethods = bean.getClass().getDeclaredMethods();
           for (Method method :declaredMethods){
               String key = bean.getClass().getInterfaces()[0].getName()+"."+ method.getName();
                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);
                Mediator.map.put(key,beanMethod);
           }
       }

        return bean;
    }
}
