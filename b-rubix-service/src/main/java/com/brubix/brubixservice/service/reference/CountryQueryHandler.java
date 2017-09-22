package com.brubix.brubixservice.service.reference;

import com.brubix.brubixservice.controller.reference.country.CountryData;

import java.util.List;

public interface CountryQueryHandler {

    List<CountryData> findAllCountries();

    CountryData findCountryByCode(String code);

}
