package com.bk.sec06;

import com.bk.models.sec06.BalanceCheckRequest;
import com.google.protobuf.Empty;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
class Lec01UnaryBlockingClientTest extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(Lec01UnaryBlockingClientTest.class);

    @Test
    public void getBalanceTest()
    {
        var request = BalanceCheckRequest.newBuilder()
                .setAccountNumber(1)
                .build();

        var balance = this.blockingStub.getAccountBalance(request);
        log.info("unary balance received: {}",balance);

        Assertions.assertEquals(100,balance.getBalance());
    }

    @Test
    public void getAllAccounts()
    {
        var allAccounts = this.blockingStub.getAllAccounts(Empty.newBuilder().build());
        log.info("unary allAccounts size: {}",allAccounts.getAccountsCount());
        Assertions.assertEquals(10,allAccounts.getAccountsList().size());
        Assertions.assertEquals(10, allAccounts.getAccountsCount());
    }
}