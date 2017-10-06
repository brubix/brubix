package com.brubix.brubixservice.service.reference.subject;

import com.brubix.brubixservice.controller.reference.subject.SubjectForm;
import com.brubix.brubixservice.repository.inventory.SubjectRepository;
import com.brubix.entity.inventory.Subject;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SubjectCommandHandlerImpl implements SubjectCommandHandler {

    private SubjectRepository subjectRepository;

    public SubjectCommandHandlerImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Void save(List<SubjectForm.SubjectData> data) {
        log.info("Storing subjects");
        List<Subject> subjects =
                data.stream()
                        .map(subjectData -> mapToEntity(subjectData))
                        .collect(Collectors.toList());
        subjectRepository.save(subjects);
        return null;
    }

    @Override
    public Subject mapToEntity(SubjectForm.SubjectData subjectData) {
        Subject subject = new Subject();
        subject.setName(subjectData.getName());
        subject.setDescription(subjectData.getDescription());
        return subject;
    }
}
