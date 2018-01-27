package com.brubix.reference.service.country;

import com.brubix.entity.reference.Country;
import com.brubix.reference.controller.country.CountryData;

import java.util.List;

public interface CountryCommandHandler {

    Country mapToEntity(CountryData countryData);

    void save(List<CountryData> data);
}
