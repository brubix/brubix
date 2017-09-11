package com.brubix.entityservice.configuration;

import com.brubix.entityservice.repository.DummyRepository;
import com.brubix.entityservice.service.DummyService;
import com.brubix.entityservice.service.DummyServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityServiceConfiguration {

    @Bean
    public DummyService dummyService(DummyRepository dummyRepository) {
        return new DummyServiceImpl(dummyRepository);
    }

}
