package com.bk.sec05;

import com.bk.models.sec05.v2.Television;
import com.bk.models.sec05.v2.Type;
import com.bk.sec05.parser.V1Parser;
import com.bk.sec05.parser.V2Parser;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V2VersionCompatibility {
    private static final Logger log = LoggerFactory.getLogger(V2VersionCompatibility.class);
    public static void main(String[] args) throws InvalidProtocolBufferException {

        var tv = Television.newBuilder()
                .setBrand("Samsung")
                .setModel(2020)
                .setType(Type.UHD)
                .build();

        V1Parser.parse(tv.toByteArray());
        System.out.print("\n");
        V2Parser.parse(tv.toByteArray());
    }
}
