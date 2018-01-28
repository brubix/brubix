package com.brubix.service.service.school;

import com.brubix.service.controller.inventory.school.CourseForm;
import com.brubix.service.controller.inventory.school.InstitutionQueryData;

import java.util.List;

public interface InstitutionQueryHandler {

    InstitutionQueryData findInstitutionByCode(String code);

    List<CourseForm.CourseData> findAllCoursesBySchoolCode(String code);
}
