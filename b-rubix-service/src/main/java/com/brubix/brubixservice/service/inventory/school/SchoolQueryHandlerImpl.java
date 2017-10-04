package com.brubix.brubixservice.service.inventory.school;

import com.brubix.brubixservice.controller.inventory.AddressData;
import com.brubix.brubixservice.controller.inventory.school.SchoolQueryData;
import com.brubix.brubixservice.exception.BrubixException;
import com.brubix.brubixservice.repository.inventory.SchoolRepository;
import com.brubix.entity.inventory.Address;
import com.brubix.entity.inventory.School;
import lombok.extern.slf4j.Slf4j;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.brubix.brubixservice.exception.error.ErrorCode.INVALID_COUNTRY_CODE;

@Slf4j
public class SchoolQueryHandlerImpl implements SchoolQueryHandler {

    private SchoolRepository schoolRepository;

    public SchoolQueryHandlerImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    @Transactional
    public SchoolQueryData findSchoolByCode(String code) {
        School school = schoolRepository.findBySchoolCode(code);

        if (school == null) {
            log.info("School code provided as {} is not found in system", code);
            throw new BrubixException(INVALID_COUNTRY_CODE);
        }

        return SchoolQueryData
                .builder()
                .addresses(mapToAddress(school.getAddresses()))
                .code(school.getSchoolCode())
                .name(school.getSchoolName())
                .logo(school.getLogo() != null ? school.getLogo().getContent() : null)
                .build();
    }

    private List<AddressData> mapToAddress(List<Address> addresses) {
        return addresses
                .stream()
                .map(address -> {
                    return AddressData
                            .builder()
                            .firstLine(address.getFirstLine())
                            .secondLine(address.getSecondLine())
                            .thirdLine(address.getThirdLine())
                            .pinCode(address.getPinCode())
                            .countryCode(address.getCountry().getCode())
                            .stateCode(address.getState().getCode())
                            .build();

                }).collect(Collectors.toList());
    }
}
