package com.brubix.identity.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Sanjaya on 27/01/18.
 */
@EnableTransactionManagement
@EntityScan(basePackages = "com.brubix.entity")
@EnableJpaRepositories(basePackages = {"com.brubix.identity.repository"})
@Configuration
public class TestRepositoryConfiguration {
}
