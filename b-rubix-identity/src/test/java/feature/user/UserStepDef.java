package feature.user;

import com.brubix.common.repository.RoleRepository;
import com.brubix.common.repository.UserRepository;
import com.brubix.common.service.BrubixUserDetails;
import com.brubix.entity.communication.Email;
import com.brubix.entity.communication.Phone;
import com.brubix.entity.inventory.Address;
import com.brubix.entity.inventory.Institution;
import com.brubix.entity.inventory.MileStone;
import com.brubix.entity.inventory.NonFaculty;
import com.brubix.identity.repository.CountryRepository;
import com.brubix.identity.repository.PrivilegeRepository;
import com.brubix.identity.repository.InstitutionRepository;
import com.brubix.identity.repository.StateRepository;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractStepDef;
import feature.SharedDataContext;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
    private InstitutionRepository schoolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository userRepository;

    private NonFaculty nonFaculty;
    private Institution savedSchool;
    private String role;
    private String clientId;
    private String secret;
    private String access_token;
    private String refresh_token;


    @Given("^the user provided school name - \"([^\"]*)\" and below addresses$")
    public void theUserProvidedSchoolNameAsAndBelowAddresses(String name, List<TestAddressData> addressDataList) {
        TestAddressData testAddressData1 = addressDataList.get(0);
        Institution school = new Institution();
        school.setInstitutionName(name);
        school.setInstitutionCode("SCHL2017121201" + name);

        Address address = new Address();
        address.setPinCode(testAddressData1.getPinCode());
        address.setFirstLine(testAddressData1.getFirstLine());
        address.setSecondLine(testAddressData1.getSecondLine());
        address.setThirdLine(testAddressData1.getThirdLine());
        address.setCountry(countryRepository.findByCode(testAddressData1.getCountryCode()));
        address.setState(stateRepository.findByCode(testAddressData1.getStateCode()));
        school.setAddress(address);

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

    @Given("^below \"([^\"]*)\" is going to be created in system$")
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
        nonFaculty.setInstitution(savedSchool);
        nonFaculty.setRoles(roleRepository.findByNameIn(Arrays.asList(role)));
        userRepository.save(nonFaculty);
    }

    @And("below client is already registered with identity service")
    public void clientRegisteredWithIdentity(List<Map> maps) {
        Map<String, String> clientSecret = maps.get(0);
        clientId = clientSecret.get("client id");
        secret = clientSecret.get("secret");
    }

    @When("proxy service calls identity service to get access token for user")
    public void userCallsIdentityForToken(List<Map> maps) {
        Map<String, String> data = maps.get(0);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", data.get("user name"));
        map.add("password", data.get("password"));
        map.add("grant_type", "password");
        map.add("client_id", clientId);
        map.add("client_secret", secret);


        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        String url = "http://localhost:" + serverPort + contextPath + "/oauth/token";

        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        SharedDataContext.setResponseEntity(response);
    }

    @Then("proxy service should get access token and details")
    public void proxyServiceReceivedAccessTokenAndDetails() throws Exception {
        String response = SharedDataContext.getResponseEntity().getBody();
        JSONObject jsonObject = new JSONObject(response);
        access_token = (String) jsonObject.get("access_token");
        refresh_token = (String) jsonObject.get("refresh_token");
        assertThat(access_token).isNotNull();
        assertThat(jsonObject.get("token_type")).isNotNull();
        assertThat(refresh_token).isNotNull();
        assertThat(jsonObject.get("expires_in")).isNotNull();
        assertThat(jsonObject.get("scope")).isNotNull();
    }

    @When("calling me api with access token")
    public void callingMeApi() throws Exception {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("Authorization", "Bearer " + access_token);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(map);
        String url = "http://localhost:" + serverPort + contextPath + "/me";
        BrubixUserDetails brubixUserDetails = restTemplate.exchange(new URI(url), HttpMethod.GET, request,
                BrubixUserDetails.class).getBody();
        SharedDataContext.setBrubixUserDetails(brubixUserDetails);
    }

    @Then("we should get role as")
    public void shouldGetRoleAs(List<Map<String, String>> table) {
        Map<String, String> roleData = table.get(0);
        BrubixUserDetails brubixUserDetails = SharedDataContext.getBrubixUserDetails();
        Assertions.assertThat(roleData.get("role")).isEqualTo(brubixUserDetails.getRoles().get(0).getName());
        Assertions.assertThat(roleData.get("description")).isEqualTo(brubixUserDetails.getRoles().get(0).getDescription());
    }

    @Then("we should get privilege as")
    public void weShouldGetPrivilegeAs(List<BrubixUserDetails.RolePrivilege> expectedPrivileges) {
        List<BrubixUserDetails.RolePrivilege> actualPrivileges = SharedDataContext.getBrubixUserDetails().getRoles().get(0).getPrivileges();
        Assertions.assertThat(expectedPrivileges).hasSize(actualPrivileges.size());
        List<Tuple> actualPrivilegesTuples = actualPrivileges
                .stream()
                .map(rolePrivilege -> {
                    return new Tuple(rolePrivilege.getName(), rolePrivilege.getDescription());
                }).collect(Collectors.toList());

        Assertions.assertThat(expectedPrivileges).extracting("name", "description")
                .containsExactlyElementsOf(actualPrivilegesTuples);
    }

    @And("^we should get associated school as \"([^\"]*)\"$")
    public void shouldGetAssociatedSchoolAs(String schoolName) {
        BrubixUserDetails.AssociatedInstitution expectedSchool = SharedDataContext.getBrubixUserDetails().getInstitution();
        Assertions.assertThat(expectedSchool.getName()).isEqualTo(schoolName);
        Assertions.assertThat(expectedSchool.getCode()).isNotBlank();
    }

    @When("called check token end point by providing access token")
    public void whenCalledValidateAccessToken() {
        String url = "http://localhost:" + serverPort + contextPath + "/oauth/check_token?token=" + access_token;
        ResponseEntity<String> data = restTemplate.getForEntity(url, String.class);
        SharedDataContext.setResponseEntity(data);
    }

    @Then("should get user name as \"([^\"]*)\"")
    public void shouldGetUserNameAs(String userName) throws Exception {
        ResponseEntity<String> responseEntity = SharedDataContext.getResponseEntity();
        Assertions.assertThat(new JSONObject(responseEntity.getBody()).get("user_name")).isEqualTo(userName);
    }

    @When("wait for \"([^\"]*)\" seconds to expire access token")
    public void waitForTokenToExpire(String time) throws Exception {
        Thread.sleep(Integer.parseInt(time) * 1000);
    }

    @When("called refresh token end point by providing details")
    public void whenCalledRefreshTokenEndPoint() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("client_id", clientId);
        map.add("client_secret", secret);
        map.add("refresh_token", refresh_token);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        String url = "http://localhost:" + serverPort + contextPath + "/oauth/token";
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        SharedDataContext.setResponseEntity(response);
    }
}
