package com.brubix.connect.email;

import com.sendgrid.*;

import java.io.IOException;

public class MailSender {

    public static void main(String[] args) throws IOException {
        Email from = new Email("test@example.com");
        String subject = "Email Verification from Brubix";
        Email to = new Email("rameshkumar.saxena@gmail.com");
        Content content = new Content("text/html", "<b>Please verify your email address<b>");
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
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}