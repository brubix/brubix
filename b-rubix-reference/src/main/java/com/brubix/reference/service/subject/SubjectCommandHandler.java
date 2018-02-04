package com.brubix.reference.service.subject;

import com.brubix.entity.reference.Subject;
import com.brubix.reference.controller.subject.SubjectRequest;

import java.util.List;

public interface SubjectCommandHandler {

    Subject mapToEntity(SubjectRequest.SubjectData subjectData);

    Void save(List<SubjectRequest.SubjectData> data);
}
