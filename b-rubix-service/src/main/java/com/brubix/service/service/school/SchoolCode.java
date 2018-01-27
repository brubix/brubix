package com.brubix.service.service.school;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
public class SchoolCode {

    private String name;
    private String code;

    private SchoolCode(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
