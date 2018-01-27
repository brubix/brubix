package com.brubix.service.controller.inventory.school;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
public class CourseForm {

    @JsonIgnore
    private String schoolCode;

    @Valid
    @NotEmpty(message = "{field.empty}")
    private List<CourseData> courses;


    @Getter
    @Setter
    public static class CourseData {

        @NotBlank(message = "{field.empty}")
        @Length(max = 100, message = "{invalid.length.course.name}")
        private String name;

        @NotBlank(message = "{field.empty}")
        @Length(max = 255, message = "{invalid.length.course.description}")
        private String description;

        @Valid
        private List<SubjectForm.SubjectData> subjects;
    }
}