package com.brubix.brubixservice.service.reference.country;

import com.brubix.brubixservice.controller.reference.country.CityData;
import com.brubix.brubixservice.controller.reference.country.CountryData;
import com.brubix.brubixservice.controller.reference.country.StateData;
import com.brubix.brubixservice.exception.BrubixException;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.entity.reference.City;
import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.State;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.brubix.brubixservice.exception.error.ErrorCode.INVALID_COUNTRY_CODE;

@Slf4j
public class CountryQueryHandlerImpl implements CountryQueryHandler {

    private CountryRepository countryRepository;

    public CountryQueryHandlerImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    @Transactional
    public CountryData findCountryByCode(String code) {
        Country country = countryRepository.findByCode(code);
        if (country == null) {
            log.info("Country code provided as {} is not found in system", code);
            throw new BrubixException(INVALID_COUNTRY_CODE);
        }

        CountryData countryData = mapToCountry(country);

        List<StateData> states = country.getStates()
                .stream()
                .map(state -> mapToState(state))
                .collect(Collectors.toList());

        countryData.setStates(states);
        return countryData;
    }

    @Override
    public List<CountryData> findAllCountries() {
        List<CountryData> countries = countryRepository.findAll()
                .stream()
                .map(country -> {
                    CountryData countryData = mapToCountry(country);
                    List<StateData> states = country.getStates()
                            .stream()
                            .map(state -> mapToState(state))
                            .collect(Collectors.toList());
                    countryData.setStates(states);

                    return countryData;
                }).collect(Collectors.toList());
        return countries;
    }

    private CountryData mapToCountry(Country country) {
        CountryData countryData = new CountryData();
        countryData.setCode(country.getCode());
        countryData.setDescription(country.getDescription());
        countryData.setCurrency(country.getCurrency());
        return countryData;
    }

    private StateData mapToState(State state) {
        StateData stateData = new StateData();
        stateData.setCode(state.getCode());
        stateData.setDescription(state.getDescription());
        stateData.setCities(state
                .getCities()
                .stream()
                .map(city -> mapToCity(city))
                .collect(Collectors.toList()));
        return stateData;
    }

    private CityData mapToCity(City city) {
        CityData cityData = new CityData();
        cityData.setCode(city.getCode());
        cityData.setDescription(city.getDescription());
        return cityData;
    }
}
