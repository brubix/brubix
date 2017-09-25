package com.brubix.brubixservice.controller.inventory;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
public class KYCData {

    @NotBlank(message = "{field.empty}")
    @Length(max = 50, message = "{invalid.length.kyc.type}")
    private String type;

    @NotBlank(message = "{field.empty}")
    @Length(max = 20, message = "{invalid.length.kyc.number}")
    private String number;
}
