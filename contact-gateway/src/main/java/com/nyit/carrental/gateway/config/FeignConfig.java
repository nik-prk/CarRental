package com.nyit.carrental.gateway.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import com.nyit.carrental.gateway.feign.UserFeignClient;

import feign.Request;

@Configuration
@EnableFeignClients(basePackageClasses = { UserFeignClient.class })
public class FeignConfig {

    @Bean
    public static Request.Options requestOptions(ConfigurableEnvironment env) {
        int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 70000);
        int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 60000);

        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }
}
