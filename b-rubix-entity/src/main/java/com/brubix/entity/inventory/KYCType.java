package com.brubix.entity.inventory;

import lombok.Getter;

import java.util.Arrays;

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

    public static KYCType getType(String type) {
        return Arrays
                .stream(KYCType.values())
                .filter((kycType -> type.equals(kycType.getDescription())))
                .findFirst()
                .get();
    }
}
