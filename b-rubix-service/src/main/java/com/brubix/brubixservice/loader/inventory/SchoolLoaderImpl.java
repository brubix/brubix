package com.brubix.brubixservice.loader.inventory;

import com.brubix.brubixservice.controller.inventory.school.SchoolForm;
import com.brubix.brubixservice.exception.BrubixException;
import com.brubix.brubixservice.exception.error.ErrorCode;
import com.brubix.brubixservice.generator.CodeGenerator;
import com.brubix.brubixservice.loader.Loader;
import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.brubixservice.repository.reference.StateRepository;
import com.brubix.entity.inventory.Address;
import com.brubix.entity.inventory.School;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class SchoolLoaderImpl implements Loader<SchoolForm, School> {

    private SchoolRepository schoolRepository;
    private CountryRepository countryRepository;
    private StateRepository stateRepository;
    private CodeGenerator schoolCodeGenerator;


    public SchoolLoaderImpl(SchoolRepository schoolRepository,
                            CountryRepository countryRepository,
                            StateRepository stateRepository,
                            CodeGenerator schoolCodeGenerator) {
        this.schoolRepository = schoolRepository;
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.schoolCodeGenerator = schoolCodeGenerator;
    }

    @Override
    public void load(List<SchoolForm> data) {
        log.info("Loading of schools started");
        List<School> schools = data
                .stream()
                .map(country -> mapToEntity(country))
                .collect(Collectors.toList());
        try {
            schoolRepository.save(schools);
            log.info("Loading of schools ended");
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
        List<Address> addresses = schoolForm.getAddresses()
                .stream()
                .map(addressData -> {
                    Address address = new Address();
                    address.setFirstLine(addressData.getFirstLine());
                    address.setSecondLine(addressData.getSecondLine());
                    address.setThirdLine(addressData.getThirdLine());
                    address.setCountry(countryRepository.findByCode(addressData.getCountryCode()));
                    address.setState(stateRepository.findByCode(addressData.getStateCode()));
                    // FIXME set MileStone, Document
                    return address;
                }).collect(Collectors.toList());
        school.setAddresses(addresses);
        return school;
    }
}
