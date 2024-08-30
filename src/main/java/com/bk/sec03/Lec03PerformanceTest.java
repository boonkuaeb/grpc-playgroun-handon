package com.bk.sec03;

import com.bk.models.sec03.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Lec03PerformanceTest {
    private static final Logger logger = LoggerFactory.getLogger(Lec03PerformanceTest.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        var protoPerson = Person.newBuilder()
                .setLastName("BK")
                .setAge(20)
                .setEmail("test@mail.com")
                .setEmployed(true)
                .setSalary(1000000.12345)
                .setBankAccountNumber(1234566789012L)
                .setBalance(-100000)
                .build();
        JsonPersonDto jsonPerson = new JsonPersonDto(
                "BK",20,"test@mail.com",true,1000000.12345,1234566789012L,-100000
        );

//        for (int i = 0; i < 5; i++) {
//            runTest("json",()->json(jsonPerson));
//            runTest("proto",()->proto(protoPerson));
//            System.out.print("\n");
//        }

        json(jsonPerson);
        proto(protoPerson);
    }
    private static void proto(Person person)
    {

        try {
            var bytes = person.toByteArray();
            logger.info("proto bytes length: {}", bytes.length);
            Person.parseFrom(bytes);
        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }

    private static void json(JsonPersonDto person)
    {
        try {
            var bytes =   mapper.writeValueAsBytes(person);
            logger.info("json bytes length: {}", bytes.length);
            mapper.readValue(bytes,JsonPersonDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void runTest(String testName, Runnable runnable)
    {
        var start = System.currentTimeMillis();
        for (int i = 0; i < 5_000_000; i++) {
            runnable.run();
        }
        var end = System.currentTimeMillis();
        logger.info("time taken for {} - {} ms",testName, end - start);
    }
}
