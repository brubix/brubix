package com.brubix.brubixservice.configuration;

import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.brubixservice.controller.reference.country.CountryForm;
import com.brubix.brubixservice.generator.SchoolCodeGenerator;
import com.brubix.brubixservice.loader.Loader;
import com.brubix.brubixservice.loader.inventory.SchoolLoaderImpl;
import com.brubix.brubixservice.loader.reference.CountryLoaderImpl;
import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.brubixservice.repository.reference.StateRepository;
import com.brubix.entity.inventory.School;
import com.brubix.entity.reference.Country;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrubixServiceConfiguration {

    @Bean
    public Loader<CountryForm.CountryData, Country> countryLoader(CountryRepository countryRepository) {
        return new CountryLoaderImpl(countryRepository);
    }

    @Bean
    public Loader<SchoolForm, School> schoolLoader(SchoolRepository schoolRepository,
                                                   CountryRepository countryRepository,
                                                   StateRepository stateRepository,
                                                   SchoolCodeGenerator schoolCodeGenerator) {
        return new SchoolLoaderImpl(schoolRepository, countryRepository, stateRepository, schoolCodeGenerator);
    }

    @Bean
    public SchoolCodeGenerator schoolCodeGenerator(SchoolRepository schoolRepository) {
        return new SchoolCodeGenerator(schoolRepository);
    }
}
