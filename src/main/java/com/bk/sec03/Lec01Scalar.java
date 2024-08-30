package com.bk.sec03;

import com.bk.models.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec01Scalar {
    private static final Logger logger = LoggerFactory.getLogger(Lec01Scalar.class);

    public static void main(String[] args) {
        var person = Person.newBuilder()
                .setLastName("BK")
//                .setAge(20)
//                .setEmail("test@mail.com")
//                .setEmployed(true)
//                .setSalary(1000000.12345)
//                .setBankAccountNumber(1234566789012L)
//                .setBalance(-100000)
                .build();
        logger.info(person.toString());
    }
}
