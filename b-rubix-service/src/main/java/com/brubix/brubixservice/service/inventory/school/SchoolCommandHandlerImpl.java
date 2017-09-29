package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.KYCData;
import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.brubixservice.exception.BrubixException;
import com.brubix.brubixservice.exception.error.ErrorCode;
import com.brubix.brubixservice.generator.CodeGenerator;
import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.brubixservice.repository.reference.StateRepository;
import com.brubix.entity.content.Document;
import com.brubix.entity.inventory.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
    public SchoolCode create(SchoolForm schoolForm) {
        log.info("Loading of schools started");
        School school = mapToEntity(schoolForm);
        try {
            School savedSchool = schoolRepository.save(school);
            log.info("Loading of schools ended");

            return SchoolCode
                    .builder()
                    .code(savedSchool.getSchoolCode())
                    .name(savedSchool.getSchoolName())
                    .build();


        } catch (DataAccessException ex) {
            log.error("Error occurred" + ExceptionUtils.getStackTrace(ex.getCause()));
            throw new BrubixException(ErrorCode.LOADING_ERROR);
        }
    }

    @Override
    public School mapToEntity(SchoolForm schoolForm) {

        // validate number of KYC documents uploaded matching with KYC data provided
        if (!schoolForm.getKycDocuments().isEmpty()) {
            if (schoolForm.getKyc().size() != schoolForm.getKycDocuments().size()) {
                throw new BrubixException(ErrorCode.INVALID_KYC_FILE_UPLOADS);
            }
        }

        School school = new School();
        school.setSchoolName(schoolForm.getName());
        school.setSchoolCode(schoolCodeGenerator.generate());
        school.setLogo(schoolForm.getSchoolLogo() != null ? createDocument(schoolForm.getSchoolLogo()) : null);

        // map addresses
        List<Address> addresses = schoolForm.getAddresses()
                .stream()
                .map(addressData -> {
                    Address address = new Address();
                    address.setFirstLine(addressData.getFirstLine());
                    address.setSecondLine(addressData.getSecondLine());
                    address.setThirdLine(addressData.getThirdLine());
                    address.setPinCode(addressData.getPinCode());
                    address.setCountry(countryRepository.findByCode(addressData.getCountryCode()));
                    address.setState(stateRepository.findByCode(addressData.getStateCode()));
                    return address;
                }).collect(Collectors.toList());

        // map KYCs
        int size = CollectionUtils.isEmpty(schoolForm.getKyc()) ? 0 : schoolForm.getKyc().size();
        List<KYC> kycList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            KYCData kycData = schoolForm.getKyc().get(i);

            KYC kyc = new KYC();
            kyc.setKycType(KYCType.getType(kycData.getType()));
            kyc.setNumber(kycData.getNumber());

            MultipartFile kycFile = schoolForm.getKycDocuments().get(i);
            kyc.setDocument(kycFile != null ? createDocument(kycFile) : null);
            kycList.add(kyc);
        }

        // map Milestones
        MileStone mileStone = new MileStone();
        mileStone.setCreatedAt(new Date());
        //FIXME - association with user entity and identity
        mileStone.setCreatedBy(1);


        school.setSchoolKyc(kycList);
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
            log.error("Error occurred" + ExceptionUtils.getStackTrace(exception.getCause()));
            throw new BrubixException(ErrorCode.INVALID_FILE);
        }
    }
}
