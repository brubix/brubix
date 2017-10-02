package com.brubix.brubixservice.controller.inventory;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class KYCData {

    @Length(max = 50, message = "{invalid.length.kyc.type}")
    private String type;

    @Length(max = 20, message = "{invalid.length.kyc.number}")
    private String number;
}
