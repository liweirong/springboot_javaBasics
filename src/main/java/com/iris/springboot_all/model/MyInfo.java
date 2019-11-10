package com.iris.springboot_all.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author iris
 * @date 2019/11/10
 */
/*
配置再spring配置文件中时
@Component
@ConfigurationProperties(prefix = "my")*/
// 配置再单独文件中时@
@Configuration
@PropertySource(value="classpath:myInfo.yml")
@ConfigurationProperties(prefix = "my")
@ToString
public class MyInfo {
    @Getter
    @Setter
    private String uuid;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer age;
    @Getter
    @Setter
    private Integer number;

}
