package com.bk.sec03;

import com.bk.models.sec03.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Lec07DefaultValue {
    private static final Logger log = LoggerFactory.getLogger(Lec07DefaultValue.class);
    public static void main(String[] args) {
        var school = School.newBuilder()
                .setAddress(Address.newBuilder().setCity("BBK").build())
                .build();


        log.info("school id: {}",school.getId());
        log.info("school name: {}",school.getName());
        log.info("school address: {}",school.getAddress());
        log.info("school address.city: {}",school.getAddress().getCity());

        log.info("is default? : {}", school.getAddress().equals(Address.getDefaultInstance()));

        //has
        log.info("has address? : {}", school.hasAddress());

        // collection
        var lib = Library.newBuilder().build();
        log.info("{}",lib.getBooksList());

        // map
        var dealer = Dealer.newBuilder().build();
        log.info("{}",dealer.getInventoryMap());

        // enum
        var car = Car.newBuilder().build();
        log.info("body style: {}",car.getBodyStyle());

    }
}
