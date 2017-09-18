package com.brubix.brubixservice.controller.reference.country;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Builder
@Getter
public class CountryForm {

    @NotBlank(message = "")
    @Length(max = 3, message = "")
    private String code;

    @NotBlank
    @Length(max = 3, message = "")
    private String description;

    @NotBlank(message = "")
    @Length(max = 3, message = "")
    private String currency;

    @NotEmpty(message = "")
    private List<StateData> states;

    @Builder
    @Getter
    public static class StateData {

        @NotBlank(message = "")
        @Length(max = 3, message = "")
        private String code;

        @NotBlank
        @Length(max = 3, message = "")
        private String description;
    }
}
