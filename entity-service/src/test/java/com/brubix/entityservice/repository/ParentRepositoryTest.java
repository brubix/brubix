package com.brubix.entityservice.repository;

import com.brubix.model.Address;
import com.brubix.model.Country;
import com.brubix.model.KYC;
import com.brubix.model.MileStone;
import com.brubix.model.Parent;
import com.brubix.model.State;
import com.brubix.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

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
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Test
    public void shouldSaveTeacherDetails() {

        // given
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
        parent.setName("Mr Robin");

        Student ward = new Student();
        ward.setDateOfAdmission(new Date());
        ward.setDateOfPassout(new Date());
        ward.setDateOfBirth(new Date());
        ward.setName("Mr Student");

        MileStone studentMileStone = new MileStone();
        studentMileStone.setCreatedAt(new Date());
        studentMileStone.setCreatedBy(1);
        ward.setMileStone(studentMileStone);

        KYC wardKyc = new KYC();
        wardKyc.setAdhaarNumber("adhar number");
        ward.setKyc(wardKyc);


        KYC kyc = new KYC();
        kyc.setPanCard("pan card");
        kyc.setDrivingLicenseNumber("license");
        kyc.setAdhaarNumber("adhar number");

        Address address = new Address();
        address.setFirstLine("first line");
        address.setSecondLine("second line");
        address.setThirdLine("third line");
        address.setPinCode("pin");
        // only one record in DB, ID is 1
        address.setState(stateRepository.getOne(1L));
        address.setCountry(countryRepository.getOne(1L));

        MileStone mileStone = new MileStone();
        mileStone.setCreatedAt(new Date());
        mileStone.setCreatedBy(1);

        parent.setKyc(kyc);
        parent.setAddresses(Arrays.asList(address));
        parent.setMileStone(mileStone);
        parent.setWards(Arrays.asList(ward));


        // when
        parentRepository.save(parent);

        // then
        /*Teacher savedTeacher = teacherRepository.findOne(1L);
        assertThat(savedTeacher.getKyc())
                .extracting("panCard", "drivingLicenseNumber", "adhaarNumber")
                .contains("pan card", "license", "adhar number");

        assertThat(savedTeacher.getAddresses())
                .hasSize(1)
                .extracting("firstLine", "secondLine", "thirdLine", "pinCode", "state.name", "country.name")
                .contains(tuple("first line", "second line", "third line", "pin", "KAR", "IND"));

        assertThat(savedTeacher.getMileStone())
                .extracting("createdBy")
                .contains(1);

        assertThat(savedTeacher.getName()).isEqualTo("Mr Robin");*/
    }
}
