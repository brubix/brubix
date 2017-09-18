package com.brubix.brubixservice.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EntityScan(basePackages = "com.brubix.entity")
@EnableJpaRepositories(basePackages = {"com.brubix.brubixservice.repository"})
@Configuration
public class RepositoryConfiguration {
}
