package com.brubix.brubixservice.repository.inventory;

import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.brubixservice.repository.reference.StateRepository;
import com.brubix.entity.inventory.*;
import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.State;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.tuple;

/**
 * Created by Sanjaya on 16/09/17.
 */
//FIXME
@Ignore("Temporarily")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@DirtiesContext
// FIXME @DataJpaTest not working with classpath entities
public class ParentRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Test
    public void shouldSaveParentDetails() {
        // given
        // creating a country in DB
        Country country = new Country();
        country.setCode("IND");
        country.setCurrency("INR");
        country.setDescription("India");
        State state = new State();
        state.setCode("KAR");
        state.setDescription("Karnataka");
        state.setCountry(country);
        country.setStates(Arrays.asList(state));
        countryRepository.save(country);

        Parent parent = new Parent();
        parent.setDateOfBirth(new Date());
        parent.setName("Mr Parent");

        DocumentInfo parentKyc = new DocumentInfo();
        parentKyc.setDocumentType(DocumentType.AADHAAR);

        Address parentAddress = new Address();
        parentAddress.setFirstLine("first line");
        parentAddress.setSecondLine("second line");
        parentAddress.setThirdLine("third line");
        parentAddress.setPinCode("pin");
        // only one record in DB, ID is 1
        parentAddress.setState(stateRepository.getOne(1L));
        parentAddress.setCountry(countryRepository.getOne(1L));

        MileStone parentMileStone = new MileStone();
        parentMileStone.setCreatedAt(new Date());
        parentMileStone.setCreatedBy(1);

        parent.setDocuments(Arrays.asList(parentKyc));
        parent.setAddresses(Arrays.asList(parentAddress));
        parent.setMileStone(parentMileStone);

        // creating a ward in DB
        Student ward = new Student();
        ward.setDateOfAdmission(new Date());
        ward.setDateOfPassout(new Date());
        ward.setDateOfBirth(new Date());
        ward.setName("Mr Student");

        MileStone wardMileStone = new MileStone();
        wardMileStone.setCreatedAt(new Date());
        wardMileStone.setCreatedBy(1);
        ward.setMileStone(wardMileStone);

        DocumentInfo wardKyc = new DocumentInfo();
        wardKyc.setDocumentType(DocumentType.AADHAAR);
        ward.setDocuments(Arrays.asList(wardKyc));
        ward.setParent(parent);

        parent.setWards(Arrays.asList(ward));

        // when
        parentRepository.save(parent);

        // then
        // parent assertions
        Parent savedParent = parentRepository.findAll().get(0);
        Assertions.assertThat(savedParent.getDocuments().get(0))
                .extracting("panCard", "drivingLicenseNumber", "adhaarNumber")
                .contains("pan card", "license", "adhar number");

        Assertions.assertThat(savedParent.getAddresses())
                .hasSize(1)
                .extracting("firstLine", "secondLine", "thirdLine", "pinCode", "state.code", "country.code")
                .contains(tuple("first line", "second line", "third line", "pin", "KAR", "IND"));

        Assertions.assertThat(savedParent.getMileStone())
                .extracting("createdBy")
                .contains(1);

        // ward assertions
        Assertions.assertThat(savedParent.getWards())
                .hasSize(1)
                .extracting("name")
                .contains("Mr Student");

        Assertions.assertThat(savedParent.getName()).isEqualTo("Mr Parent");

        Assertions.assertThat(savedParent.getWards().get(0).getDocuments())
                .extracting("adhaarNumber")
                .contains("wadharnumber");

    }
}
