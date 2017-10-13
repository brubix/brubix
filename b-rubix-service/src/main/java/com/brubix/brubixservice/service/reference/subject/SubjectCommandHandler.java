package com.brubix.brubixservice.service.reference.subject;

import com.brubix.brubixservice.controller.reference.subject.SubjectForm;
import com.brubix.entity.reference.Subject;

import java.util.List;

public interface SubjectCommandHandler {

    Subject mapToEntity(SubjectForm.SubjectData subjectData);

    Void save(List<SubjectForm.SubjectData> data);
}
