package com.brubix.service.service.school;

import com.brubix.entity.inventory.Institution;
import com.brubix.service.controller.inventory.school.CourseForm;
import com.brubix.service.controller.inventory.school.SchoolForm;

public interface InstitutionCommandHandler {

    InstitutionCode create(SchoolForm schoolForm);

    Institution mapToEntity(SchoolForm schoolForm);

    void create(CourseForm courseForm);
}
