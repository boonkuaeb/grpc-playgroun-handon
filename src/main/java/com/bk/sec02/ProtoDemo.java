package com.bk.sec02;

import com.bk.models.sec02.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProtoDemo {
    private static final Logger log = LoggerFactory.getLogger(ProtoDemo.class);
    public static void main(String[] args) {
        // create person1
        var person1 = createPerson();
        log.info(person1.toString());

        // create other instance with same values
        var person2 = createPerson();
        log.info(person2.toString());

        // compare
        log.info("person1 equals person2? {}",person1.equals(person2));
        log.info("person1 == person2 ? {}", person1==person2);

        // mutable? no . It is immutable
        // person1.setXX

        // create another instance with diff values
        var person3 = person1.toBuilder()
                .setName("Nan")
                .build();
        log.info(person3.toString());

        log.info("person1 equals person3? {}",person1.equals(person3));
        log.info("person1 == person3? {}", person1==person3);

        // null? no
        // var person4 = person1.toBuilder().setName(null).build();
        // log.info("person4 {}", person4.toString());

        var person5 = person1.toBuilder().clearName().build();
        log.info("person5 {}", person5.toString());

    }

    private static Person createPerson() {
        return Person.newBuilder()
                .setName("Bk")
                .setAge(20)
                .build();
    }
}
