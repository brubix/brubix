package com.brubix.connect.service;

import com.brubix.connect.sms.SmsRequest;
import com.brubix.connect.sms.SmsResponse;

public interface SmsService {

    SmsResponse sendSms(SmsRequest request);
}
