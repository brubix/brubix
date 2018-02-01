package com.brubix.connect.controller;

import com.brubix.connect.email.EmailRequest;
import com.brubix.connect.email.EmailResponse;
import com.brubix.connect.service.EmailService;
import com.brubix.connect.service.SmsService;
import com.brubix.connect.sms.SmsRequest;
import com.brubix.connect.sms.SmsResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
@RestController
@RequestMapping(path = "/connect",
        produces = {MediaType.APPLICATION_JSON_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
public class BrubixConnectController {
    @Autowired
    EmailService emailService ;
    @Autowired
    SmsService smsService;

    @PostMapping(path = "/email")
    @ApiOperation(
            value = "Send email ",
            notes = "Send email",
            code = 204,
            response = String.class)
    public ResponseEntity<EmailResponse> sendEmail(
            @ApiParam(name = "Email",
                    value = "Email details to be sent",
                    required = true) @RequestBody @Valid EmailRequest emailRequest) {
        EmailResponse emailResponse = emailService.sendMail(emailRequest);
        return new ResponseEntity<EmailResponse>(emailResponse,HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/sms")
    @ApiOperation(
            value = "Send sms ",
            notes = "Send sms",
            code = 204,
            response = String.class)
    public ResponseEntity<SmsResponse> sendSms(
            @ApiParam(name = "SMS",
                    value = "SMS details to be sent",
                    required = true) @RequestBody @Valid SmsRequest smsRequest) {
       SmsResponse smsResponse = smsService.sendSms(smsRequest);
        return new ResponseEntity<SmsResponse>(smsResponse,HttpStatus.ACCEPTED);
    }

}
