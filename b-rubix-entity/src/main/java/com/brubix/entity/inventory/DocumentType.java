package com.brubix.entity.inventory;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum DocumentType {

    AADHAAR("Aadhaar"),
    PAN_CARD("Pan card"),
    DRIVING_LICENSE("Driving license"),
    REGISTRATION("Registration"),
    PROFILE("Profile");

    @Getter
    private String description;

    DocumentType(String description) {
        this.description = description;
    }

    public static DocumentType getType(String type) {
        return Arrays
                .stream(DocumentType.values())
                .filter((kycType -> type.equals(kycType.getDescription())))
                .findFirst()
                .get();
    }

    public static List<DocumentType> getKycDocumentTypes() {
        return Arrays.asList(AADHAAR, PAN_CARD, DRIVING_LICENSE, REGISTRATION);
    }
}
