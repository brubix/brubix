package com.brubix.reference.service.subject;

import com.brubix.common.repository.SubjectRepository;
import com.brubix.entity.reference.Subject;
import com.brubix.reference.controller.subject.SubjectRequest;
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
    public Void save(List<SubjectRequest.SubjectData> data) {
        log.info("Storing subjects");
        List<Subject> subjects =
                data.stream()
                        .map(subjectData -> mapToEntity(subjectData))
                        .collect(Collectors.toList());
        subjectRepository.saveAll(subjects);
        return null;
    }

    @Override
    public Subject mapToEntity(SubjectRequest.SubjectData subjectData) {
        Subject subject = new Subject();
        subject.setName(subjectData.getName());
        subject.setDescription(subjectData.getDescription());
        return subject;
    }
}
