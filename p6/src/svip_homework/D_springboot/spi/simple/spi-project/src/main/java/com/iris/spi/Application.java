package com.iris.spi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.ClassUtils;
import sun.misc.Service;

import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author iris
 * @date 2021/11/3
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        List<Log> logs = SpringFactoriesLoader.loadFactories(Log.class, ClassUtils.getDefaultClassLoader());
        for (Log log : logs) {
            System.out.println(log);
            log.debug();
        }


        Iterator<SPIService> providers = Service.providers(SPIService.class);
        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
        while (providers.hasNext()) {
            SPIService ser = providers.next();
            System.out.println("1 " + ser.listAllCompanyInfo());
        }
        System.out.println("--------------------------------");
        Iterator<SPIService> iterator = load.iterator();
        while (iterator.hasNext()) {
            SPIService ser = iterator.next();
            System.out.println("2 " + ser.listAllCompanyInfo());
        }
    }
}
