package com.iris.springcloud.locator;

import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author iris
 * @date 2021/12/22
 * 告诉spring cloud 加载property source
 * @Confugration注解没用 一定要spi
 */
public class MyPropertySourceLocator implements PropertySourceLocator {
    private static Map<String, Object> map = new HashMap<>();

    static {
        // 可以自定义本地文件读取然后给到spring
        map.put("user.name.locator", "iris");
    }

    @Override
    public PropertySource<?> locate(Environment environment) {
        return new MyPropertySource("test", map);
    }
}
