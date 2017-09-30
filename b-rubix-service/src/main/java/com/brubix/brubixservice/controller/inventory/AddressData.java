package com.brubix.brubixservice.controller.inventory;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Getter
@Builder
@ToString
public class AddressData {

    @NotBlank(message = "{field.empty}")
    @Length(max = 250, message = "{invalid.length.first.line.address}")
    private String firstLine;

    @Length(min = 0, max = 250, message = "{invalid.length.first.line.address}")
    private String secondLine;

    @Length(min = 0, max = 250, message = "{invalid.length.first.line.address}")
    private String thirdLine;

    @NotBlank(message = "{field.empty}")
    @Length(max = 3, message = "{invalid.state.code}")
    @Pattern(regexp = "([A-Z]){3}", message = "{invalid.state.code}")
    private String stateCode;

    @NotBlank(message = "{field.empty}")
    @Length(max = 3, message = "{invalid.length.country.code}")
    @Pattern(regexp = "([A-Z]){3}", message = "{invalid.country.code}")
    private String countryCode;
    
    @NotBlank(message = "{field.empty}")
    @Length(max = 6, message = "{invalid.first.line.address}")
    private String pinCode;
}
