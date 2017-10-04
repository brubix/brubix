package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.school.CourseForm;
import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.entity.inventory.School;

public interface SchoolCommandHandler {

    SchoolCode create(SchoolForm schoolForm);

    School mapToEntity(SchoolForm schoolForm);

    void create(CourseForm courseForm);
}
