package com.brubix.brubixservice.controller.reference.country;

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
public class StateData {

    @NotBlank(message = "{field.empty}")
    @Length(max = 3, message = "{invalid.length.state.code}")
    @Pattern(regexp = "([A-Z]){3}", message = "{invalid.state.code}")
    private String code;

    @NotBlank(message = "{field.empty}")
    @Length(max = 20, message = "{invalid.length.state.description}")
    private String description;

    @Valid
    @NotEmpty(message = "{field.empty}")
    private List<CityData> cities;
}
