package com.robin.sys.config;

import org.apache.ibatis.session.Configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Config {

    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public Configuration globalConfiguration(){
        return new Configuration();
    }
}
