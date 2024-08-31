package com.bk.sec03;

import com.bk.models.sec03.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;



public class Lec03PerformanceTest02 {

    private static final Logger logger = LoggerFactory.getLogger(Lec03PerformanceTest02.class);
    private static final Path PATH = Path.of("person.out");
    private static final ObjectMapper mapper = new ObjectMapper();


    public static void main(String[] args) throws IOException {
        var protoPersonLite = Person.newBuilder()
//                .setLastName("BK")
//                .setAge(20)
//                .setEmail("test@mail.com")
                .setEmployed(true)
//                .setSalary(1000000.12345)
//                .setBankAccountNumber(1234566789012L)
//                .setBalance(-100000)
                .build();
        JsonPersonLiteDto jsonPersonLite = new JsonPersonLiteDto(true);

        /*
        {"employed": true}
         */


        serialize(protoPersonLite);
        System.out.println("\n");
        json(jsonPersonLite);

    }

    /**
     * encoding only the value of position number 4 ( not a field name )
     * The value is True and then True is 1 so that length = 2
     * In case of FALSE. The False is default value
     * Due to the Proto trying to reduce the size as much as it can.
     * so that the length = 0
     *
     */
    public static void serialize(Person person)  {

        try {
            var bytes = person.toByteArray();
            logger.info("proto bytes length: {}", bytes.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void json(JsonPersonLiteDto person)
    {
        try {
            var bytes = mapper.writeValueAsBytes(person);
            logger.info("json bytes length: {}", bytes.length);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
