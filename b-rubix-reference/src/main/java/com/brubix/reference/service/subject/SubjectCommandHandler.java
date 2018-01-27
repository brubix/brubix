package com.brubix.reference.service.subject;

import com.brubix.entity.reference.Subject;
import com.brubix.reference.controller.subject.SubjectForm;

import java.util.List;

public interface SubjectCommandHandler {

    Subject mapToEntity(SubjectForm.SubjectData subjectData);

    Void save(List<SubjectForm.SubjectData> data);
}
