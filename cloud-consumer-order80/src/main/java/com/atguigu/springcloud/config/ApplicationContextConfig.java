package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author cj
 * @Title:
 * @JdkVersion JDK1.8
 * @ClassName
 * @Version 1.0.0
 * @date 2022/2/2020:54
 * @Description:
 */
@Configuration
public class ApplicationContextConfig {
    
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
