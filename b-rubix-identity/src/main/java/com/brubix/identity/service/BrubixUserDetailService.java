package com.brubix.identity.service;

import com.brubix.entity.identity.User;
import com.brubix.identity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class BrubixUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    public BrubixUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return new BrubixUserDetails(user.getName(), user.isEnabled(), user.getRoles());
    }
}
