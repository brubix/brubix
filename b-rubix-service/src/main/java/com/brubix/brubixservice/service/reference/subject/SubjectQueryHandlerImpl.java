package com.brubix.brubixservice.service.reference.subject;

import com.brubix.brubixservice.controller.reference.subject.SubjectForm;
import com.brubix.brubixservice.repository.inventory.SubjectRepository;
import com.brubix.entity.reference.Subject;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectQueryHandlerImpl implements SubjectQueryHandler {

    private SubjectRepository subjectRepository;

    public SubjectQueryHandlerImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<SubjectForm.SubjectData> findAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();
        return subjects
                .stream()
                .map(subject -> {
                    SubjectForm.SubjectData subjectData = new SubjectForm.SubjectData();
                    subjectData.setName(subject.getName());
                    subjectData.setDescription(subject.getDescription());
                    return subjectData;
                }).collect(Collectors.toList());
    }
}
