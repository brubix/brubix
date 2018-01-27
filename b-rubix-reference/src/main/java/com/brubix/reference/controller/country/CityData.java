package com.brubix.reference.controller.country;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by Sanjaya on 26/01/18.
 */
@Getter
@Setter
public class CityData {

    @NotBlank(message = "{field.empty}")
    @Length(max = 3, message = "{invalid.length.city.code}")
    @Pattern(regexp = "([A-Z]){3}", message = "{invalid.city.code}")
    private String code;

    @NotBlank(message = "{field.empty}")
    @Length(max = 20, message = "{invalid.length.city.description}")
    private String description;
}
