package com.brubix.identity.controller;


import com.brubix.identity.service.BrubixUserDetails;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users",
        produces = {MediaType.APPLICATION_JSON_VALUE})
@Api(tags = {"User"}, description = StringUtils.SPACE)
public class UserController {

    @GetMapping(path = "")
    @ApiOperation(
            value = "Get logged in user",
            notes = "Get logged in user",
            response = BrubixUserDetails.class)
    public ResponseEntity<BrubixUserDetails> user() {
        BrubixUserDetails userDetails =
                (BrubixUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }
}
