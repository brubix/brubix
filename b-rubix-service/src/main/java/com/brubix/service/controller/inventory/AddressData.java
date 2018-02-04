package com.brubix.service.controller.inventory;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class AddressData {

    @NotBlank(message = "{field.empty}")
    @Length(max = 250, message = "{invalid.length.first.line.address}")
    private String firstLine;

    @Length(min = 0, max = 250, message = "{invalid.length.first.line.address}")
    private String secondLine;

    @Length(min = 0, max = 250, message = "{invalid.length.first.line.address}")
    private String thirdLine;

    @NotBlank(message = "{field.empty}")
    @Length(max = 3, message = "{invalid.length.state.code}")
    @Pattern(regexp = "([A-Z]){3}", message = "{invalid.state.code}")
    private String state;

    @NotBlank(message = "{field.empty}")
    @Length(max = 3, message = "{invalid.length.country.code}")
    @Pattern(regexp = "([A-Z]){3}", message = "{invalid.country.code}")
    private String country;

    @NotBlank(message = "{field.empty}")
    @Length(max = 3, message = "{invalid.length.city.code}")
    @Pattern(regexp = "([A-Z]){3}", message = "{invalid.city.code}")
    private String city;

    
    @NotBlank(message = "{field.empty}")
    @Length(max = 6, message = "{invalid.first.line.address}")
    private String pin;
}
