
package feature;

import com.brubix.brubixservice.BrubixServiceApplication;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ContextConfiguration(classes = BrubixServiceApplication.class, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class AbstractStep {

    @Getter
    @Value("${server.context-path}")
    protected String contextPath;

    @Autowired
    protected RestTemplate restTemplate;

    @Getter
    @LocalServerPort
    protected int serverPort;

}
