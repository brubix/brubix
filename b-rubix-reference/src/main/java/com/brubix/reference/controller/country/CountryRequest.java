package com.brubix.reference.controller.country;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class CountryRequest {

    @Valid
    @NotEmpty(message = "field.empty")
    private List<CountryData> countries;
}
