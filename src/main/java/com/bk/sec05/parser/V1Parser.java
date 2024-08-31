package com.bk.sec05.parser;

import com.bk.models.sec05.v1.Television;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class V1Parser {
    private static final Logger logger = LoggerFactory.getLogger(V1Parser.class);

    public static void parse(byte[] bytes) throws InvalidProtocolBufferException
    {
        var tv = Television.parseFrom(bytes);
        logger.info("brand: {}", tv.getBrand());
        logger.info("year: {}", tv.getYear());

    }
}
