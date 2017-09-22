package feature.reference;

import com.brubix.brubixservice.controller.reference.country.CountryData;
import com.brubix.brubixservice.controller.reference.country.CountryForm;
import com.brubix.brubixservice.controller.reference.country.StateData;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import feature.AbstractStep;
import org.springframework.http.*;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CountryStepDef extends AbstractStep {

    private List<CountryData> countries;

    @Given("^that below countries loaded in system$")
    public void that_below_countries_loaded_in_system(List<CountryData> countries) {
        this.countries = countries;
    }

    @And("^below states for \"([^\"]*)\" are associated$")
    public void below_states(String countryCode, List<StateData> states) {
        countries
                .stream()
                .filter(countryData -> countryData.getCode().equals(countryCode))
                .findFirst()
                .get()
                .setStates(states);
    }

    @When("^user loaded country data into system$")
    public void user_loaded_country_data_into_system() throws Exception {
        CountryForm countryForm = new CountryForm();
        countryForm.setCountries(countries);

        ObjectMapper objectMapper = new ObjectMapper();
        String data = objectMapper.writeValueAsString(countryForm);

        String countryEndPoint = "http://localhost:" + serverPort + contextPath + "/countries";
        HttpEntity<String> httpEntity = new HttpEntity<>(data, buildHeaders());
        ResponseEntity responseEntity = restTemplate.postForEntity(new URI(countryEndPoint), httpEntity, String.class);
        assertThat(responseEntity.getStatusCode().value())
                .isEqualTo(204);
    }

    @Then("^we should see \"([^\"]*)\" has below states$")
    public void country_has_below_states(String countryCode, List<StateData> states) {

    }

    public static HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    protected <T> ResponseEntity exchange(String url, HttpMethod httpMethod, HttpEntity<?> httpEntity,
                                          Class<T> responseClass) {
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, responseClass);
        System.out.println(responseEntity.getBody() != null ? responseEntity.getBody().toString() : "Empty body");
        return responseEntity;
    }

}
