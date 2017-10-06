package com.brubix.brubixservice.controller.reference.country;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class CountryForm {

    @Valid
    @NotEmpty(message = "field.empty")
    private List<CountryData> countries;
}
