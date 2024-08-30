package com.bk.sec02;

import com.bk.models.sec02.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProtoDemo {
    private static final Logger log = LoggerFactory.getLogger(ProtoDemo.class);
    public static void main(String[] args) {

        var person = Person.newBuilder()
                .setName("Bk")
                .setAge(20)
                .build();
        log.info(person.toString());
        log.info("{}",person);
    }
}
