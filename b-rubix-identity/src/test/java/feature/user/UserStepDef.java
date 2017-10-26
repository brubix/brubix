package feature.user;

import com.brubix.entity.communication.Email;
import com.brubix.entity.communication.Phone;
import com.brubix.entity.inventory.Address;
import com.brubix.entity.inventory.MileStone;
import com.brubix.entity.inventory.NonFaculty;
import com.brubix.entity.inventory.School;
import com.brubix.identity.repository.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import feature.AbstractStepDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class UserStepDef extends AbstractStepDef {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    private UserRepository userRepository;

    private NonFaculty nonFaculty;
    private School savedSchool;
    private String role;


    @Given("^the user provided school name - \"([^\"]*)\" and below addresses$")
    public void theUserProvidedSchoolNameAsAndBelowAddresses(String name, List<TestAddressData> addressDataList) {
        School school = new School();
        school.setSchoolName(name);
        school.setSchoolCode("SCHL2017121201");
        school.setAddresses(addressDataList.stream()
                .map(testAddressData1 -> {
                    Address address = new Address();
                    address.setPinCode(testAddressData1.getPinCode());
                    address.setFirstLine(testAddressData1.getFirstLine());
                    address.setSecondLine(testAddressData1.getSecondLine());
                    address.setThirdLine(testAddressData1.getThirdLine());
                    address.setCountry(countryRepository.findByCode(testAddressData1.getCountryCode()));
                    address.setState(stateRepository.findByCode(testAddressData1.getStateCode()));
                    return address;
                }).collect(Collectors.toList()));

        MileStone mileStone = new MileStone();
        mileStone.setCreatedAt(new Date());
        mileStone.setCreatedBy(1);
        school.setMileStone(mileStone);
        savedSchool = schoolRepository.save(school);
    }


    @Given("^all country data present in system$")
    public void givenAllCountryReferenceDataPresent() {
        assertThat(countryRepository.findAll().size()).isGreaterThan(0);
    }

    @Given("^all role data present in system$")
    public void givenAllRoleReferenceDataPresent() {
        assertThat(roleRepository.findAll().size()).isGreaterThan(0);
    }

    @Given("^all privilege data present in system$")
    public void givenAllPrivilegeReferenceDataPresent() {
        assertThat(privilegeRepository.findAll().size()).isGreaterThan(0);
    }

    @And("below address provided")
    public void belowAddressProvided(List<TestAddressData> testAddressData) {
        nonFaculty.setAddresses(testAddressData.stream()
                .map(testAddressData1 -> {
                    Address address = new Address();
                    address.setPinCode(testAddressData1.getPinCode());
                    address.setFirstLine(testAddressData1.getFirstLine());
                    address.setSecondLine(testAddressData1.getSecondLine());
                    address.setThirdLine(testAddressData1.getThirdLine());
                    address.setCountry(countryRepository.findByCode(testAddressData1.getCountryCode()));
                    address.setState(stateRepository.findByCode(testAddressData1.getStateCode()));
                    return address;
                }).collect(Collectors.toList()));

    }

    @Given("^below \"([^\"]*)\" is going be created in system$")
    public void belowUserCreatedInSystem(String role, List<NonFaculty> users) {
        this.role = role;
        nonFaculty = users.get(0);
        nonFaculty.setPassword(passwordEncoder.encode(nonFaculty.getPassword()));
    }

    @And("below phone number provided")
    public void belowPhoneNumberProvided(List<Phone> phones) {
        nonFaculty.setPhones(phones);
        phones.forEach(phone -> phone.setUser(nonFaculty));
    }

    @And("below emails provided")
    public void belowEmailNumberProvided(List<Email> emails) {
        nonFaculty.setEmails(emails);
        emails.forEach(email -> email.setUser(nonFaculty));
    }

    @And("user created")
    public void userCreated() {
        MileStone mileStone = new MileStone();
        mileStone.setCreatedAt(new Date());
        mileStone.setCreatedBy(1);

        nonFaculty.setMileStone(mileStone);
        nonFaculty.setEnabled(true);
        savedSchool.setNonFaculties(Arrays.asList(nonFaculty));
        nonFaculty.setSchool(savedSchool);
        nonFaculty.setRoles(roleRepository.findByNameIn(Arrays.asList(role)));

        userRepository.save(nonFaculty);
    }

}
