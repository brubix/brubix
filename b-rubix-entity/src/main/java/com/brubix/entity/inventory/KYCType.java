package com.brubix.entity.inventory;

import lombok.Getter;

public enum KYCType {

    AADHAAR("Aadhaar"),
    PAN_CARD("Pan Card"),
    DRIVING_LICENSE("Driving License"),
    REGISTRATION("Registration");

    @Getter
    private String description;

    KYCType(String description) {
        this.description = description;
    }
}
