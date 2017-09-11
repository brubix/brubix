package com.brubix.entityservice.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EntityScan(basePackages = {"com.brubix.entityservice.entity"})
@EnableJpaRepositories(basePackages = {"com.brubix.entityservice.repository"})
@Configuration
public class RepositoryConfiguration {
}
