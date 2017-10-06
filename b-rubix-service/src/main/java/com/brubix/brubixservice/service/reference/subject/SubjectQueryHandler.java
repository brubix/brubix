package com.brubix.brubixservice.service.reference.subject;

import com.brubix.brubixservice.controller.reference.subject.SubjectForm;

import java.util.List;

public interface SubjectQueryHandler {

    List<SubjectForm.SubjectData> findAllSubjects();
}
