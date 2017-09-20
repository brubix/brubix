package com.brubix.brubixservice.loader.reference;

import com.brubix.brubixservice.controller.reference.country.CountryForm;
import com.brubix.brubixservice.exception.BrubixException;
import com.brubix.brubixservice.exception.error.ErrorCode;
import com.brubix.brubixservice.loader.Loader;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.State;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CountryLoaderImpl implements Loader<CountryForm.CountryData, Country> {

    private CountryRepository countryRepository;

    public CountryLoaderImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void load(List<CountryForm.CountryData> data) {
        log.info("Loading of countries started");
        List<Country> countries = data
                .stream()
                .map(country -> mapToEntity(country))
                .collect(Collectors.toList());

        try {
            countryRepository.save(countries);
            log.info("Loading of countries ended");
        } catch (Exception ex) {
            log.error("Error occurred" + ex);
            throw new BrubixException(ErrorCode.LOADING_ERROR);
        }
    }

    @Override
    public Country mapToEntity(CountryForm.CountryData countryData) {

        Country country = new Country();
        country.setCode(countryData.getCode());
        country.setDescription(countryData.getDescription());
        country.setCurrency(countryData.getCurrency());

        List<State> states = countryData.getStates().stream().map(stateData -> {
            State state = new State();
            state.setCode(stateData.getCode());
            state.setDescription(stateData.getDescription());
            state.setCountry(country);
            return state;
        }).collect(Collectors.toList());
        country.setStates(states);
        return country;
    }
}
