package com.brubix.entityservice.repository.inventory;

import com.brubix.entityservice.repository.reference.CountryRepository;
import com.brubix.entityservice.repository.reference.StateRepository;
import com.brubix.model.inventory.*;
import com.brubix.model.reference.Country;
import com.brubix.model.reference.State;
import org.assertj.core.api.Assertions;
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
        country.setName("IND");
        country.setCurrency("INR");
        country.setDescription("India");
        State state = new State();
        state.setName("KAR");
        state.setDescription("Karnataka");
        state.setCountry(country);
        country.setStates(Arrays.asList(state));
        countryRepository.save(country);

        Parent parent = new Parent();
        parent.setDateOfBirth(new Date());
        parent.setName("Mr Parent");

        KYC parentKyc = new KYC();
        parentKyc.setPanCard("pan card");
        parentKyc.setDrivingLicenseNumber("license");
        parentKyc.setAdhaarNumber("adhar number");

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

        parent.setKyc(parentKyc);
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

        KYC wardKyc = new KYC();
        wardKyc.setAdhaarNumber("wadharnumber");
        ward.setKyc(wardKyc);
        ward.setParent(parent);

        parent.setWards(Arrays.asList(ward));

        // when
        parentRepository.save(parent);

        // then
        // parent assertions
        Parent savedParent = parentRepository.findAll().get(0);
        Assertions.assertThat(savedParent.getKyc())
                .extracting("panCard", "drivingLicenseNumber", "adhaarNumber")
                .contains("pan card", "license", "adhar number");

        Assertions.assertThat(savedParent.getAddresses())
                .hasSize(1)
                .extracting("firstLine", "secondLine", "thirdLine", "pinCode", "state.name", "country.name")
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

        Assertions.assertThat(savedParent.getWards().get(0).getKyc())
                .extracting("adhaarNumber")
                .contains("wadharnumber");

    }
}
