package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cj
 * @Title:
 * @JdkVersion JDK1.8
 * @ClassName
 * @Version 1.0.0
 * @date 2022/4/110:56
 * @Description:
 */
@Configuration
public class MyselfRule {

    @Bean
    public IRule myRule() {
        // 自定义随机负载均衡 Ribbon
        return new RandomRule();
    }
}
