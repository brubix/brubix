package com.brubix.brubixservice.controller.inventory.school;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CourseQueryData {

    private String name;

    private String description;

    private List<SubjectQueryData> subjects;

    @Getter
    @Builder
    public static class SubjectQueryData {

        private String name;
        private String description;
    }
}
