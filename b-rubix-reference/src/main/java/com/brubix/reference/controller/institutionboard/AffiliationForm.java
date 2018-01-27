package com.brubix.reference.controller.institutionboard;

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
public class AffiliationForm {

    @Valid
    @NotEmpty(message = "field.empty")
    private List<AffiliationData> boards;


    @Builder
    @Getter
    public static class AffiliationData {

        @NotBlank(message = "{field.empty}")
        @Length(max = 100, message = "{invalid.length.affiliation}")
        private String affiliation;

        @NotBlank
        @Length(max = 255, message = "{invalid.length.affiliation.description}")
        private String description;
    }
}
