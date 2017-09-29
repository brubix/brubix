package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.entity.inventory.School;

public interface SchoolCommandHandler {

    SchoolCode create(SchoolForm data);

    School mapToEntity(SchoolForm schoolForm);
}