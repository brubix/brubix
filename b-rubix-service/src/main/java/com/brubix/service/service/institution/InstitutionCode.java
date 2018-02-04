package com.brubix.service.service.institution;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
public class InstitutionCode {

    private String name;
    private String code;

    private InstitutionCode(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
