package com.bk.sec05.parser;

import com.bk.models.sec05.v2.Television;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class V2Parser {
    private static final Logger logger = LoggerFactory.getLogger(V2Parser.class);

    public static void parse(byte[] bytes) throws InvalidProtocolBufferException
    {
        var tv = Television.parseFrom(bytes);
        logger.info("brand: {}", tv.getBrand());
        logger.info("model: {}", tv.getModel());
        logger.info("type: {}", tv.getType());
        logger.info(tv.getAllFields().toString());


    }
}
