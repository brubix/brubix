package com.brubix.brubixservice.controller.reference.subject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class SubjectForm {

    @Valid
    @NotEmpty(message = "field.empty")
    private List<SubjectData> subjects;

    @Getter
    @Setter
    @ToString
    public static class SubjectData {

        @NotBlank(message = "{field.empty}")
        @Length(max = 100, message = "{invalid.length.subject.name}")
        private String name;


        @NotBlank(message = "{field.empty}")
        @Length(max = 255, message = "{invalid.length.subject.description}")
        private String description;
    }
}
