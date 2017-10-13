package com.brubix.brubixservice.service.reference.country;

import com.brubix.brubixservice.controller.reference.country.CountryData;
import com.brubix.entity.reference.Country;

import java.util.List;

public interface CountryCommandHandler {

    Country mapToEntity(CountryData countryData);

    void save(List<CountryData> data);
}
