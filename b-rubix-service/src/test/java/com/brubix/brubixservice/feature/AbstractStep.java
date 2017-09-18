
package com.brubix.brubixservice.feature;

import com.brubix.brubixservice.configuration.BrubixServiceConfiguration;
import com.brubix.brubixservice.configuration.RepositoryConfiguration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ContextConfiguration(
        classes = {BrubixServiceConfiguration.class, RepositoryConfiguration.class})
@SpringBootTest(webEnvironment = RANDOM_PORT)
public abstract class AbstractStep {

    @Getter
    @Value("${server.context-path}")
    protected String contextPath;

    @Autowired
    protected TestRestTemplate restTemplate;

    @Getter
    @LocalServerPort
    protected int serverPort;

}
