package com.brubix.common.service;

import com.brubix.entity.identity.User;
import com.brubix.entity.identity.VerificationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by Sanjaya on 28/01/18.
 */
public interface BrubixUserDetailsService extends UserDetailsService {

    User getUser(String verificationToken);

    VerificationToken getVerificationToken(String VerificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);
}
