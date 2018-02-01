package com.brubix.connect.service.impl;

import com.brubix.connect.service.SmsService;
import com.brubix.connect.sms.SmsRequest;
import com.brubix.connect.sms.SmsResponse;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public SmsResponse sendSms(SmsRequest request) {
        SmsResponse smsResponse = sendSmsByNexmo(request);
        return smsResponse;
    }


    private  SmsResponse sendSmsByNexmo(SmsRequest request) {
        SmsResponse smsResponse = new SmsResponse();
        String   NEXMO_API_KEY ="bb5952a8" ;
        String   NEXMO_API_SECRET ="a612ebccfb2821ad" ;
        AuthMethod auth = new TokenAuthMethod(NEXMO_API_KEY, NEXMO_API_SECRET);
        NexmoClient client = new NexmoClient(auth);

        SmsSubmissionResult[] responses = new SmsSubmissionResult[0];
        try {
            responses = client.getSmsClient().submitMessage(new TextMessage(
                    request.getFrom(),
                    request.getPhoneNumber(),
                    request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NexmoClientException e) {
            e.printStackTrace();
        }
        for (SmsSubmissionResult response : responses) {
            System.out.println(response);
            smsResponse.setMessage(response.getMessageId());
        }

        return smsResponse ;
    }

}
