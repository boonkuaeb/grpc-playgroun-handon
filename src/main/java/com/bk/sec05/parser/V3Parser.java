package com.bk.sec05.parser;

import com.bk.models.sec05.v3.Television;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class V3Parser {
    private static final Logger logger = LoggerFactory.getLogger(V3Parser.class);

    public static void parse(byte[] bytes) throws InvalidProtocolBufferException
    {
        var tv = Television.parseFrom(bytes);
        logger.info("brand: {}", tv.getBrand());
        logger.info("type: {}", tv.getType());
        logger.info(tv.getAllFields().toString());


    }
}
