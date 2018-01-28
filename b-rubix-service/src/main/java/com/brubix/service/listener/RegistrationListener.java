package com.brubix.service.listener;

import com.brubix.entity.communication.Email;
import com.brubix.entity.identity.User;
import com.brubix.service.event.OnRegistrationCompleteEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

import java.util.UUID;

/**
 * Created by Sanjaya on 28/01/18.
 */
@Slf4j
public class RegistrationListener {

    @EventListener
    public void processEmailVerification(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        // service.createVerificationToken(user, token);
        Email email = user.getEmails().get(0);
    }
}