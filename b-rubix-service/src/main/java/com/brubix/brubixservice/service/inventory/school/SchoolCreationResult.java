package com.brubix.brubixservice.service.inventory.school;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SchoolCreationResult {

    private String name;
    private String code;

    private SchoolCreationResult(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
