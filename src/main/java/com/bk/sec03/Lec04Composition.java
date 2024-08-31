package com.bk.sec03;

import com.bk.models.sec03.Address;
import com.bk.models.sec03.School;
import com.bk.models.sec03.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Lec04Composition {
    private static final Logger log = LoggerFactory.getLogger(Lec04Composition.class);

    public static void main(String[] args) {

        // create student
        var address = Address.newBuilder()
                .setStreet("160 main street")
                .setCity("atlanta")
                .setStage("GA")
                .build();
        var student = Student.newBuilder()
                .setAddress(address)
                .setName("Sam")
                .build();

        // create school
        var school = School.newBuilder()
                .setId(1)
                .setName("high school")
                .setAddress(
                            address.toBuilder()
                            .setStreet("494 main street ")
                            .build()
                );
        log.info("school: {}", school);
        log.info("student: {}", student);


    }
}
