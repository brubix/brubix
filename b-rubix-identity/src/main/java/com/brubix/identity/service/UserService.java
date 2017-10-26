package com.brubix.identity.service;

public interface UserService {

    BrubixUserDetails getUserDetailsByIdentifier(String identifier);
}
