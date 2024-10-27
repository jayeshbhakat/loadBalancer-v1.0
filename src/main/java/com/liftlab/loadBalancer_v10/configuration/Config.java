package com.liftlab.loadBalancer_v10.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {

    @Bean
    public RestTemplate getRestTemplateBean(){
        return new RestTemplate();
    }

}
