package com.brubix.brubixservice.controller.reference;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
public class CountryData {

    @NotBlank(message = "{field.empty}")
    @Length(max = 3, message = "{invalid.length.country.code}")
    @Pattern(regexp = "([A-Z]){3}", message = "{invalid.country.code}")
    private String code;

    @NotBlank
    @Length(max = 20, message = "{invalid.length.country.description}")
    private String description;

    @NotBlank(message = "{field.empty}")
    @Length(max = 3, message = "{invalid.length.currency}")
    @Pattern(regexp = "([A-Z]){3}", message = "{invalid.currency.code}")
    private String currency;

    @Valid
    @NotEmpty(message = "{field.empty}")
    private List<StateData> states;
}