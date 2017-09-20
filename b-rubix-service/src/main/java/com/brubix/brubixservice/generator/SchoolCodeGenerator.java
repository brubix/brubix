package com.brubix.brubixservice.generator;

import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.entity.inventory.School;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class SchoolCodeGenerator implements CodeGenerator {

    private SchoolRepository schoolRepository;

    public SchoolCodeGenerator(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public synchronized String generate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMdd");
        String date = dateFormat.format(new Date());
        School school = schoolRepository.findTopByOrderByIdDesc();
        if (school == null) {
            return String.format("%s%s%s", getPrefix(), 1, date);
        } else {
            return String.format("%s%s%s", getPrefix(), school.getId(), date);
        }
    }

    @Override
    public String getPrefix() {
        return "SCHL";
    }
}
