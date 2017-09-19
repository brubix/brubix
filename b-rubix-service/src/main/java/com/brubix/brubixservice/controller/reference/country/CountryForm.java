package com.brubix.brubixservice.controller.reference.country;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

@Setter
@Getter
public class CountryForm {

    @Valid
    @NotEmpty(message = "")
    private List<CountryData> countryData;

    @Setter
    @Getter
    public static class CountryData {

        @NotBlank(message = "field.empty")
        @Length(max = 3, message = "invalid.country.code")
        private String code;

        @NotBlank
        @Length(max = 20, message = "invalid.country.description")
        private String description;

        @NotBlank(message = "field.empty")
        @Length(max = 3, message = "invalid.country.currency")
        private String currency;

        @Valid
        @NotEmpty(message = "field.empty")
        private List<StateData> states;
    }

    @Setter
    @Getter
    public static class StateData {

        @NotBlank(message = "field.empty")
        @Length(max = 3, message = "invalid.state.code")
        private String code;

        @NotBlank
        @Length(max = 3, message = "invalid.state.description")
        private String description;
    }
}
