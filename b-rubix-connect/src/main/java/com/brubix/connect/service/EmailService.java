package com.brubix.connect.service;

import com.brubix.connect.email.EmailRequest;
import com.brubix.connect.email.EmailResponse;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
 EmailResponse sendMail(EmailRequest emailRequest);
}
