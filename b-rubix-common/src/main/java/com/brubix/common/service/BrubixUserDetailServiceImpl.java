package com.brubix.common.service;

import com.brubix.common.repository.UserRepository;
import com.brubix.common.repository.VerificationTokenRepository;
import com.brubix.entity.identity.User;
import com.brubix.entity.identity.VerificationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class BrubixUserDetailServiceImpl implements BrubixUserDetailsService {

    private final UserRepository userRepository;

    private final VerificationTokenRepository verificationTokenRepository;

    public BrubixUserDetailServiceImpl(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {

        User user = userRepository.findByEmailId(identifier);
        if (user == null) {
            user = userRepository.findByPhoneNumber(identifier);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
        }
        return new BrubixUserDetails(identifier, user.getPassword(), user.isEnabled());
    }


    @Override
    public User getUser(String verificationToken) {
        User user = verificationTokenRepository.findByToken(verificationToken).getUser();
        return user;
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken();
        myToken.setToken(token);
        myToken.setUser(user);
        verificationTokenRepository.save(myToken);
    }

}
