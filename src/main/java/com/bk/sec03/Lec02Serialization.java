package com.bk.sec03;

import com.bk.models.sec03.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Lec02Serialization {

    private static final Logger logger = LoggerFactory.getLogger(Lec02Serialization.class);
    private static final Path PATH = Path.of("person.out");

    public static void main(String[] args) throws IOException {
        var person = Person.newBuilder()
                .setLastName("BK")
                .setAge(20)
                .setEmail("test@mail.com")
                .setEmployed(true)
                .setSalary(1000000.12345)
                .setBankAccountNumber(1234566789012L)
                .setBalance(-100000)
                .build();
       // logger.info(person.toString());

        serialize(person);
        logger.info("deserialize: {}",deserialize());
        logger.info("person equals deserialized: {}",deserialize());
        logger.info("bytes length: {}",person.toByteArray().length);

    }

    public static void serialize(Person person) throws IOException {
        try(var stream = Files.newOutputStream(PATH))
        {
            person.writeTo(stream);
        }
    }

    public static Person deserialize() throws IOException {
        try(var stream = Files.newInputStream(PATH))
        {
            return Person.parseFrom(stream);
        }
    }
}
