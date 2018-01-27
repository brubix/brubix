package com.brubix.reference.service.country;

import com.brubix.common.exception.BrubixException;
import com.brubix.common.exception.error.ErrorCode;
import com.brubix.common.repository.CountryRepository;
import com.brubix.entity.reference.City;
import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.State;
import com.brubix.reference.controller.country.CountryData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CountryCommandHandlerImpl implements CountryCommandHandler {

    private CountryRepository countryRepository;

    public CountryCommandHandlerImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void save(List<CountryData> data) {
        log.info("Loading of countries started");
        List<Country> countries = data
                .stream()
                .map(country -> mapToEntity(country))
                .collect(Collectors.toList());

        try {
            countryRepository.save(countries);
            log.info("Loading of countries ended");
        } catch (DataAccessException ex) {
            log.error("Error occurred" + ExceptionUtils.getStackTrace(ex.getCause()));
            throw new BrubixException(ErrorCode.LOADING_ERROR);
        }
    }

    @Override
    public Country mapToEntity(CountryData countryData) {

        Country country = new Country();
        country.setCode(countryData.getCode());
        country.setDescription(countryData.getDescription());
        country.setCurrency(countryData.getCurrency());
        country.setDialingCode(countryData.getDialingCode());
        List<State> states = countryData
                .getStates()
                .stream()
                .map(stateData -> {
                    State state = new State();
                    state.setCode(stateData.getCode());
                    state.setDescription(stateData.getDescription());
                    List<City> cities = state
                            .getCities()
                            .stream()
                            .map(s -> {
                                City city = new City();
                                city.setCode(s.getCode());
                                city.setDescription(s.getDescription());
                                return city;
                            }).collect(Collectors.toList());
                    state.setCities(cities);
                    state.setCountry(country);
                    return state;
                }).collect(Collectors.toList());
        country.setStates(states);
        return country;
    }
}
