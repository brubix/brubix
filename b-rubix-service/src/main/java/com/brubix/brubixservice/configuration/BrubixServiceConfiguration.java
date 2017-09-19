package com.brubix.brubixservice.configuration;

import com.brubix.brubixservice.controller.reference.country.CountryForm;
import com.brubix.brubixservice.loader.Loader;
import com.brubix.brubixservice.loader.reference.country.CountryLoaderImpl;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.entity.reference.Country;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BrubixServiceConfiguration {

    @Bean
    public Loader<CountryForm.CountryData, Country> countryLoader(CountryRepository countryRepository) {
        return new CountryLoaderImpl(countryRepository);
    }

}
