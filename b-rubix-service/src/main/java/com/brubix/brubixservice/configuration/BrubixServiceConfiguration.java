package com.brubix.brubixservice.configuration;

import com.brubix.brubixservice.generator.SchoolCodeGenerator;
import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.brubixservice.repository.reference.StateRepository;
import com.brubix.brubixservice.service.inventory.school.SchoolCommandHandler;
import com.brubix.brubixservice.service.inventory.school.SchoolCommandHandlerImpl;
import com.brubix.brubixservice.service.reference.CountryCommandHandler;
import com.brubix.brubixservice.service.reference.CountryCommandHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BrubixServiceConfiguration {

    @Bean
    public CountryCommandHandler countryCommandHandler(CountryRepository countryRepository) {
        return new CountryCommandHandlerImpl(countryRepository);
    }

    @Bean
    public SchoolCommandHandler schoolCommandHandler(SchoolRepository schoolRepository,
                                                     CountryRepository countryRepository,
                                                     StateRepository stateRepository,
                                                     SchoolCodeGenerator schoolCodeGenerator) {
        return new SchoolCommandHandlerImpl(schoolRepository, countryRepository, stateRepository, schoolCodeGenerator);
    }

    @Bean
    public SchoolCodeGenerator schoolCodeGenerator(SchoolRepository schoolRepository) {
        return new SchoolCodeGenerator(schoolRepository);
    }
}
