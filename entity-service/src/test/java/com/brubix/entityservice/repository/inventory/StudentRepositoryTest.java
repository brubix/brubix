package com.brubix.entityservice.repository.inventory;

import com.brubix.entityservice.repository.reference.CountryRepository;
import com.brubix.entityservice.repository.reference.StateRepository;
import com.brubix.model.inventory.Address;
import com.brubix.model.reference.Country;
import com.brubix.model.inventory.KYC;
import com.brubix.model.inventory.MileStone;
import com.brubix.model.reference.State;
import com.brubix.model.inventory.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

/**
 * Created by Sanjaya on 16/09/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@DirtiesContext
// FIXME @DataJpaTest not working with classpath entities
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Test
    public void shouldSaveStudentDetails() {

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

        Student student = new Student();
        student.setDateOfAdmission(new Date());
        student.setDateOfPassout(new Date());
        student.setDateOfBirth(new Date());
        student.setName("Mr Robin");

        KYC kyc = new KYC();
        kyc.setPanCard("pan card");
        kyc.setDrivingLicenseNumber("license");
        kyc.setAdhaarNumber("adhar number");

        Address address = new Address();
        address.setFirstLine("first line");
        address.setSecondLine("second line");
        address.setThirdLine("third line");
        address.setPinCode("pin");
        // only one record in DB, so ID is 1
        address.setState(stateRepository.getOne(1L));
        address.setCountry(countryRepository.getOne(1L));

        MileStone mileStone = new MileStone();
        mileStone.setCreatedAt(new Date());
        mileStone.setCreatedBy(1);

        student.setKyc(kyc);
        student.setAddresses(Arrays.asList(address));
        student.setMileStone(mileStone);

        // when
        studentRepository.save(student);

        // then
        Student savedStudent = studentRepository.findOne(1L);
        assertThat(savedStudent.getKyc())
                .extracting("panCard", "drivingLicenseNumber", "adhaarNumber")
                .contains("pan card", "license", "adhar number");

        assertThat(savedStudent.getAddresses())
                .hasSize(1)
                .extracting("firstLine", "secondLine", "thirdLine", "pinCode", "state.name", "country.name")
                .contains(tuple("first line", "second line", "third line", "pin", "KAR", "IND"));

        assertThat(savedStudent.getMileStone())
                .extracting("createdBy")
                .contains(1);

        assertThat(savedStudent.getDateOfAdmission()).isBeforeOrEqualsTo(new Date());
        assertThat(savedStudent.getDateOfPassout()).isBeforeOrEqualsTo(new Date());
        assertThat(savedStudent.getName()).isEqualTo("Mr Robin");
    }
}
