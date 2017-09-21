package com.brubix.brubixservice.controller.reference.country;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Builder
public class CountryForm {

    @Valid
    @NotEmpty(message = "field.empty")
    private List<CountryData> countries;

    @Getter
    @Builder
    public static class CountryData {

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

    @Getter
    @Builder
    public static class StateData {

        @NotBlank(message = "{field.empty}")
        @Length(max = 3, message = "{invalid.length.state.code}")
        @Pattern(regexp = "([A-Z]){3}", message = "{invalid.state.code}")
        private String code;

        @NotBlank(message = "{field.empty}")
        @Length(max = 20, message = "{invalid.length.state.description}")
        private String description;
    }
}
