package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.entity.inventory.School;

import java.util.List;

public interface SchoolCommandHandler {

    List<SchoolCreationResult> load(List<SchoolForm> data);

    public School mapToEntity(SchoolForm schoolForm);
}
