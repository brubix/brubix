package com.brubix.service.service.institution;

import com.brubix.entity.inventory.Institution;
import com.brubix.service.controller.inventory.school.CourseForm;
import com.brubix.service.controller.inventory.school.InstitutionCreateRequest;

public interface InstitutionCommandHandler {

    InstitutionCode create(InstitutionCreateRequest schoolForm);

    Institution mapToEntity(InstitutionCreateRequest schoolForm);

    void create(CourseForm courseForm);
}
