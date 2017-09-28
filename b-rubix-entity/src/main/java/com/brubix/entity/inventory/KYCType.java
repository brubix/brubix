package com.brubix.entity.inventory;

import lombok.Getter;

public enum KYCType {

    AADHAAR("Aadhaar"),
    PAN_CARD("Pan card"),
    DRIVING_LICENSE("Driving license"),
    REGISTRATION("Registration");

    @Getter
    private String description;

    KYCType(String description) {
        this.description = description;
    }
}
