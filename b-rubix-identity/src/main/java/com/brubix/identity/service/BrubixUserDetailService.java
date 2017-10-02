package com.brubix.identity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class BrubixUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        return null;
    }
}
