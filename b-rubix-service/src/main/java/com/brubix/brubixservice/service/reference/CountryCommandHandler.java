package com.brubix.brubixservice.service.reference;

import com.brubix.brubixservice.controller.reference.CountryData;
import com.brubix.entity.reference.Country;

import java.util.List;

public interface CountryCommandHandler {

    Country mapToEntity(CountryData countryData);

    Void save(List<CountryData> data);
}
