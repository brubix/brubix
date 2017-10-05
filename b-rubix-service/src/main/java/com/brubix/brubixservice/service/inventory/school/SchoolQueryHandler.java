package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.school.CourseQueryData;
import com.brubix.brubixservice.controller.inventory.school.SchoolQueryData;

import java.util.List;

public interface SchoolQueryHandler {

    SchoolQueryData findSchoolByCode(String code);

    List<CourseQueryData> findAllCoursesBySchoolCode(String code);
}
