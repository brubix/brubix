package com.brubix.connect.service.impl;

import com.brubix.connect.email.EmailRequest;
import com.brubix.connect.email.EmailResponse;
import com.brubix.connect.service.EmailService;
import com.sendgrid.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public EmailResponse sendMail(EmailRequest emailRequest) {
        EmailResponse emailResponse = new EmailResponse();
        Email from = new Email(emailRequest.getFrom());
        String subject = emailRequest.getSubject();
        Email to = new Email(emailRequest.getTo());
        Content content = new Content("text/html", emailRequest.getContent());
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
            emailResponse.setMessage(response.getBody());
        } catch (IOException ex) {
            ex.printStackTrace();
            emailResponse.setMessage("Failed to send email");
        }
        return emailResponse;
     }
}
