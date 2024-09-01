package com.bk.sec06;

import com.bk.models.sec06.BalanceCheckRequest;
import com.bk.models.sec06.BankServiceGrpc;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GrpcClient {
    private static final Logger log = LoggerFactory.getLogger(GrpcClient.class);
    public static void main(String[] args) {


        var channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        var stub = BankServiceGrpc.newBlockingStub(channel);
        var balance = stub.getAccountBalance(BalanceCheckRequest.newBuilder().setAccountNumber(11).build());

        log.info(balance.toString());

    }
}
