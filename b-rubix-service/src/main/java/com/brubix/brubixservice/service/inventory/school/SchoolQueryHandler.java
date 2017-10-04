package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.school.SchoolQueryData;

public interface SchoolQueryHandler {

    SchoolQueryData findSchoolByCode(String code);
}
