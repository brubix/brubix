package com.brubix.brubixservice.configuration;

import com.brubix.brubixservice.controller.BrubixRestErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RemoteConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new BrubixRestErrorHandler());
        return restTemplate;
    }
}
