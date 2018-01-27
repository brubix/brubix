package com.brubix.reference.controller.language;

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
public class LanguageForm {


    @Valid
    @NotEmpty(message = "field.empty")
    private List<LanguageData> languages;

    @Getter
    @Builder
    public static class LanguageData {
        @NotBlank(message = "{field.empty}")
        @Length(max = 100, message = "{invalid.length.language}")
        private String language;

        @NotBlank
        @Length(max = 255, message = "{invalid.length.language.description}")
        private String description;
    }
}
