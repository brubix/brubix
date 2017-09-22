package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.brubixservice.exception.BrubixException;
import com.brubix.brubixservice.exception.error.ErrorCode;
import com.brubix.brubixservice.generator.CodeGenerator;
import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.brubixservice.repository.reference.StateRepository;
import com.brubix.entity.content.Document;
import com.brubix.entity.inventory.Address;
import com.brubix.entity.inventory.MileStone;
import com.brubix.entity.inventory.School;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SchoolCommandHandlerImpl implements SchoolCommandHandler {

    private SchoolRepository schoolRepository;
    private CountryRepository countryRepository;
    private StateRepository stateRepository;
    private CodeGenerator schoolCodeGenerator;


    public SchoolCommandHandlerImpl(SchoolRepository schoolRepository,
                                    CountryRepository countryRepository,
                                    StateRepository stateRepository,
                                    CodeGenerator schoolCodeGenerator) {
        this.schoolRepository = schoolRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.schoolCodeGenerator = schoolCodeGenerator;
    }

    @Override
    public List<SchoolCode> load(List<SchoolForm> data) {
        log.info("Loading of schools started");
        List<School> schools = data
                .stream()
                .map(schoolData -> mapToEntity(schoolData))
                .collect(Collectors.toList());
        try {
            List<School> savedSchools = schoolRepository.save(schools);
            log.info("Loading of schools ended");

            return savedSchools
                    .stream()
                    .map(school -> {
                        return SchoolCode
                                .builder()
                                .code(school.getSchoolCode())
                                .name(school.getSchoolName())
                                .build();
                    }).collect(Collectors.toList());

        } catch (Exception ex) {
            log.error("Error occurred" + ex);
            throw new BrubixException(ErrorCode.LOADING_ERROR);
        }
    }

    @Override
    public School mapToEntity(SchoolForm schoolForm) {
        School school = new School();
        school.setSchoolName(schoolForm.getName());
        school.setSchoolCode(schoolCodeGenerator.generate());
        school.setSchoolLogo(createDocument(schoolForm.getSchoolLogoFile()));
        List<Address> addresses = schoolForm.getAddresses()
                .stream()
                .map(addressData -> {
                    Address address = new Address();
                    address.setFirstLine(addressData.getFirstLine());
                    address.setSecondLine(addressData.getSecondLine());
                    address.setThirdLine(addressData.getThirdLine());
                    address.setCountry(countryRepository.findByCode(addressData.getCountryCode()));
                    address.setState(stateRepository.findByCode(addressData.getStateCode()));
                    return address;
                }).collect(Collectors.toList());

        MileStone mileStone = new MileStone();
        mileStone.setCreatedAt(new Date());
        //FIXME - association with user entity and identity
        mileStone.setCreatedBy(1);
        school.setAddresses(addresses);
        school.setMileStone(mileStone);
        return school;
    }

    private Document createDocument(MultipartFile file) {
        try {
            Document document = new Document();
            document.setDocumentName(file.getName());
            document.setContent(file.getBytes());
            document.setMimeType(file.getContentType());
            return document;
        } catch (IOException exception) {
            log.error("Error occurred" + exception);
            throw new BrubixException(ErrorCode.INVALID_FILE);
        }
    }
}
