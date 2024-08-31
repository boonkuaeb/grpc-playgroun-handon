package com.bk.sec04;

import com.bk.models.sec04.Sample;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class Lec02WellKnownType {
    private static final Logger log = LoggerFactory.getLogger(Lec02WellKnownType.class);
    public static void main(String[] args) {

        var sample = Sample.newBuilder()
                .setAge(Int32Value.of(20))
                .setLoginTime(Timestamp.newBuilder().setSeconds(Instant.now().getEpochSecond()).build())
                .build();
        log.info(sample.toString());
        log.info("{}", Instant.ofEpochSecond(sample.getLoginTime().getSeconds(), sample.getLoginTime().getSeconds()));
    }
}