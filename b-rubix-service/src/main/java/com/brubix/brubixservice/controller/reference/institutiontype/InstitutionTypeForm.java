package com.brubix.brubixservice.controller.reference.institutiontype;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class InstitutionTypeForm {

    @Valid
    @NotEmpty(message = "field.empty")
    private List<InstitutionTypeData> types;


    @Builder
    @Getter
    public static class InstitutionTypeData {
        @NotBlank(message = "{field.empty}")
        @Length(max = 100, message = "{invalid.length.institution.type}")
        private String type;

        @NotBlank
        @Length(max = 255, message = "{invalid.length.institution.type.description}")
        private String description;
    }
}
