package com.bk.sec04;


import com.bk.models.common.Address;
import com.bk.models.common.BodyStyle;
import com.bk.models.common.Car;
import com.bk.models.sec04.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Lec01Import {
    private static final Logger log = LoggerFactory.getLogger(Lec01Import.class);
    public static void main(String[] args) {
        var address = Address.newBuilder()
                .setStreet("1234")
                .setCity("London")
                .setStage("USA")
                .build();
        var car = Car.newBuilder()
                .setMake("Toyota")
                .setModel("Yaris Cross")
                .setYear(2024)
                .setBodyStyle(BodyStyle.SUV)
                .build();

        var person = Person.newBuilder()
                .setName("BK")
                .setAge(20)
                .setAddress(address)
                .setCar(car)
                .build();
        log.info(person.toString());

    }
}
