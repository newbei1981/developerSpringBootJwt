package com.newbie.springsecurityjwt.service.twilio;

import com.twilio.Twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class SmsSender {
    public static final String ACCOUNT_SID = System.getenv("ACf8d892324df5caca2a38934b2bc41095");
    public static final String AUTH_TOKEN = System.getenv("de76b3f37f0c2bfdb9d35eb942bcec59");

    public  String sendSms(String userPhoneNumber){

        String smsText = Long.toHexString(Double.doubleToLongBits(Math.random())).substring(0,6);
        System.out.println(smsText);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber(userPhoneNumber), // to
                        new PhoneNumber("+14405307880"), // from
                        smsText)
                .create();
        return smsText;
    }
}
