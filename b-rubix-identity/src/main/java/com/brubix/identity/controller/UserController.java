package com.brubix.identity.controller;


import com.brubix.common.service.BrubixUserDetails;
import com.brubix.common.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/me",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {"User"}, description = StringUtils.SPACE)
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "")
    @ApiOperation(
            value = "Get logged in user details",
            notes = "Get logged in user details",
            response = BrubixUserDetails.class)
    public ResponseEntity<Principal> user(Principal principal) {
        BrubixUserDetails brubixUserDetails = userService.getUserDetailsByIdentifier(principal.getName());
        return new ResponseEntity(brubixUserDetails, HttpStatus.OK);
    }
}
