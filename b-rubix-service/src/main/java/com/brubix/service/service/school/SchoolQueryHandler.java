package com.brubix.service.service.school;

import com.brubix.service.controller.inventory.school.CourseForm;
import com.brubix.service.controller.inventory.school.SchoolQueryData;

import java.util.List;

public interface SchoolQueryHandler {

    SchoolQueryData findSchoolByCode(String code);

    List<CourseForm.CourseData> findAllCoursesBySchoolCode(String code);
}
