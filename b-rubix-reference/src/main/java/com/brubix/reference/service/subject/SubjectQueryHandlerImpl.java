package com.brubix.reference.service.subject;

import com.brubix.common.repository.SubjectRepository;
import com.brubix.entity.reference.Subject;
import com.brubix.reference.controller.subject.SubjectRequest;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectQueryHandlerImpl implements SubjectQueryHandler {

    private SubjectRepository subjectRepository;

    public SubjectQueryHandlerImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<SubjectRequest.SubjectData> findAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects
                .stream()
                .map(subject -> {
                    SubjectRequest.SubjectData subjectData = new SubjectRequest.SubjectData();
                    subjectData.setName(subject.getName());
                    subjectData.setDescription(subject.getDescription());
                    return subjectData;
                }).collect(Collectors.toList());
    }
}
