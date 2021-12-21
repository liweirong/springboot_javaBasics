package com.iris.springcloud.locator;

import org.springframework.core.env.MapPropertySource;

import java.util.Map;

/**
 * @author iris
 * @date 2021/12/22
 * 可以不重写 用来给到environment中的propertySource
 */
public class MyPropertySource extends MapPropertySource {
    public MyPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public Object getProperty(String name) {
        return super.getProperty(name);
    }

    @Override
    public String[] getPropertyNames() {
        return super.getPropertyNames();
    }
}
