package com.brubix.reference.service.country;

import com.brubix.reference.controller.country.CountryData;

import java.util.List;

public interface CountryQueryHandler {

    List<CountryData> findAllCountries();

    CountryData findCountryByCode(String code);

}
