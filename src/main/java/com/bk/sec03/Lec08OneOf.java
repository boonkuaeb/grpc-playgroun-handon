package com.bk.sec03;

import com.bk.models.sec03.Credentials;
import com.bk.models.sec03.Email;
import com.bk.models.sec03.Phone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Lec08OneOf {
    private static final Logger log = LoggerFactory.getLogger(Lec08OneOf.class);

    // Client
    public static void main(String[] args) {
        var email = Email.newBuilder().setAddress("test@mail.com").setPassword("amdin").build();
        var phone = Phone.newBuilder().setNumber(123456789).setCode(123).build();

        login(Credentials.newBuilder().setEmail(email).build());
        login(Credentials.newBuilder().setPhone(phone).build());
        login(Credentials.newBuilder().setEmail(email).setPhone(phone).build());
        login(Credentials.newBuilder().setPhone(phone).setEmail(email).build());

    }

    // Server
    private static void login(Credentials credentials)
    {
        switch (credentials.getLoginTypeCase())
        {
            case EMAIL -> log.info("email -> {}",credentials.getEmail());
            case PHONE -> log.info("phone -> {}",credentials.getPhone());
        }
    }
}
