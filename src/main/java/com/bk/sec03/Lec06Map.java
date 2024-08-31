package com.bk.sec03;

import com.bk.models.sec03.BodyStyle;
import com.bk.models.sec03.Car;
import com.bk.models.sec03.Dealer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Lec06Map {
    private static final Logger log = LoggerFactory.getLogger(Lec06Map.class);
    public static void main(String[] args) {
        var car1 = Car.newBuilder()
                .setMake("honda")
                .setModel("Civic")
                .setYear(2020)
                .setBodyStyle(BodyStyle.COUPE)
                .build();
        var car2 = Car.newBuilder()
                .setMake("honda")
                .setModel("Accord")
                .setYear(2021)
                .setBodyStyle(BodyStyle.SEDAN)
                .build();

        var dealer = Dealer.newBuilder()
                .putInventory(car1.getYear(),car1)
                .putInventory(car2.getYear(),car2)
                .build();
        log.info("dealer: {}" , dealer);

        log.info("car2 getBodyStyle: {}" , car2.getBodyStyle());

        log.info("\n2020? : {}" , dealer.containsInventory(2020));
        log.info("2021? : {}" , dealer.containsInventory(2021));

        log.info("\n2020 model? : {}" , dealer.getInventoryOrThrow(2020).getModel());
        log.info("2021 model? : {}" , dealer.getInventoryOrThrow(2021));
        log.info("2021 body style? : {}" , dealer.getInventoryOrThrow(2021).getBodyStyle());

    }
}
