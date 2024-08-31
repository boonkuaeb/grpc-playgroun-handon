package com.bk.sec05;

import com.bk.models.sec05.v3.Television;
import com.bk.models.sec05.v3.Type;
import com.bk.sec05.parser.V1Parser;
import com.bk.sec05.parser.V2Parser;
import com.bk.sec05.parser.V3Parser;
import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V3VersionCompatibility {
    private static final Logger log = LoggerFactory.getLogger(V3VersionCompatibility.class);
    public static void main(String[] args) throws InvalidProtocolBufferException {

        var tv = Television.newBuilder()
                .setBrand("Samsung")
                .setType(Type.UHD)
                .build();

        V1Parser.parse(tv.toByteArray());
        System.out.print("\n");
        V2Parser.parse(tv.toByteArray());
        System.out.print("\n");
        V3Parser.parse(tv.toByteArray());
    }
}
