package com.brubix.connect;

import com.brubix.connect.email.EmailRequest;
import com.brubix.connect.sms.SmsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BrubixConnectApplication.class}, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = DEFINED_PORT)
@org.junit.Ignore
public class BrubixConnectTest {

    @Test
    public void testSendMail() {
        RestTemplate restTemplate = new RestTemplate();
        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setFrom("test@example.com");
        emailRequest.setTo("rameshkumar.saxena@gmail.com");
        emailRequest.setSubject("test mail from brubix");
        emailRequest.setContent("Test content");
        HttpEntity<EmailRequest> request = new HttpEntity<>(emailRequest);
        restTemplate.postForObject("http://localhost:8083/v1/connect/email", request, String.class);
    }

    @Test
    public void testSendSms() {
        RestTemplate restTemplate = new RestTemplate();
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setFrom("Brubix");
        smsRequest.setPhoneNumber("919945488182");
        smsRequest.setContent("test mail from bruix ");
        // emailRequest.setContent("Test content");
        HttpEntity<SmsRequest> request = new HttpEntity<>(smsRequest);
        restTemplate.postForObject("http://localhost:8083/v1/connect/sms", request, String.class);

    }

}
