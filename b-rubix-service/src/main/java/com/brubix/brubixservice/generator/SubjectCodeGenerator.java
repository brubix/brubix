package com.brubix.brubixservice.generator;

import com.brubix.brubixservice.repository.inventory.SubjectRepository;
import com.brubix.entity.inventory.Subject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SubjectCodeGenerator implements CodeGenerator {

    private SubjectRepository subjectRepository;

    public SubjectCodeGenerator(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public synchronized String generate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMdd");
        String date = dateFormat.format(new Date());
        Subject subject = subjectRepository.findTopByOrderByIdDesc();
        if (subject == null) {
            return String.format("%s%s%s", getPrefix(), 1, date);
        } else {
            return String.format("%s%s%s", getPrefix(), subject.getId() + 1, date);
        }
    }

    @Override
    public String getPrefix() {
        return "SUBJ";
    }
}
