package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.school.CourseForm;
import com.brubix.brubixservice.controller.inventory.school.SchoolQueryData;

import java.util.List;

public interface SchoolQueryHandler {

    SchoolQueryData findSchoolByCode(String code);

    List<CourseForm.CourseData> findAllCoursesBySchoolCode(String code);
}
