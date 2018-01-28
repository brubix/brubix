package com.brubix.service.generator;

import com.brubix.entity.inventory.Institution;
import com.brubix.service.repository.inventory.InstitutionRepository;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class SchoolCodeGenerator implements CodeGenerator {

    private InstitutionRepository schoolRepository;

    public SchoolCodeGenerator(InstitutionRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public synchronized String generate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMdd");
        String date = dateFormat.format(new Date());
        Institution school = schoolRepository.findTopByOrderByIdDesc();
        if (school == null) {
            return String.format("%s%s%s", getPrefix(), 1, date);
        } else {
            return String.format("%s%s%s", getPrefix(), school.getId() + 1, date);
        }
    }

    @Override
    public String getPrefix() {
        return "SCHL";
    }
}
