package com.brubix.brubixservice.controller.reference;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

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
}
