package com.brubix.service.service.school;

import com.brubix.service.controller.inventory.school.CourseForm;
import com.brubix.service.controller.inventory.school.SchoolForm;
import com.brubix.entity.inventory.School;

public interface SchoolCommandHandler {

    SchoolCode create(SchoolForm schoolForm);

    School mapToEntity(SchoolForm schoolForm);

    void create(CourseForm courseForm);
}
