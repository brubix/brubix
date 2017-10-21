package com.brubix.brubixservice.repository.inventory;

import com.brubix.brubixservice.repository.reference.CountryRepository;
import com.brubix.brubixservice.repository.reference.StateRepository;
import com.brubix.entity.inventory.*;
import com.brubix.entity.reference.Country;
import com.brubix.entity.reference.State;
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

import static org.assertj.core.api.Assertions.assertThat;
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
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Test
    public void shouldSaveTeacherDetails() {

        // given
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

        Teacher teacher = new Teacher();
        teacher.setJoiningDate(new Date());
        teacher.setResignationDate(new Date());
        teacher.setDateOfBirth(new Date());
        teacher.setName("Mr Robin");

        DocumentInfo document = new DocumentInfo();
        document.setDocumentType(DocumentType.AADHAAR);

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

        teacher.setDocuments(Arrays.asList(document));
        teacher.setAddresses(Arrays.asList(address));
        teacher.setMileStone(mileStone);

        // when
        teacherRepository.save(teacher);

        // then
        Teacher savedTeacher = teacherRepository.findOne(1L);
        assertThat(savedTeacher.getDocuments().get(0))
                .extracting("panCard", "drivingLicenseNumber", "adhaarNumber")
                .contains("pan card", "license", "adhar number");

        assertThat(savedTeacher.getAddresses())
                .hasSize(1)
                .extracting("firstLine", "secondLine", "thirdLine", "pinCode", "state.code", "country.code")
                .contains(tuple("first line", "second line", "third line", "pin", "KAR", "IND"));

        assertThat(savedTeacher.getMileStone())
                .extracting("createdBy")
                .contains(1);

        assertThat(savedTeacher.getName()).isEqualTo("Mr Robin");
    }
}
