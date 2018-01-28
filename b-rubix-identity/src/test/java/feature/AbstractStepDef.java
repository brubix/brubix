
package feature;

import com.brubix.identity.IdentityApiApplication;
import com.brubix.identity.configuration.TestRepositoryConfiguration;
import gherkin.deps.com.google.gson.Gson;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ContextConfiguration(classes = {ReferenceDataLoader.class, IdentityApiApplication.class,
        TestRepositoryConfiguration.class
},
        loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class AbstractStepDef {

    protected final Gson gson = new Gson();

    @Getter
    @Value("${server.context-path}")
    protected String contextPath;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Getter
    @LocalServerPort
    protected int serverPort;

    protected static HttpHeaders buildHeaders() {
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
