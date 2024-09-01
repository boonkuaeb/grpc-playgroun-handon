package com.bk.sec06;
import com.bk.models.sec06.AccountBalance;
import com.bk.models.sec06.BalanceCheckRequest;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class Lec02UnaryAsyncClient01Test extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(Lec02UnaryAsyncClient01Test.class);

    @Test
    public void getAsyncBalanceTest() throws InterruptedException {
        var request = BalanceCheckRequest.newBuilder()
                .setAccountNumber(1)
                .build();
        var latch =  new CountDownLatch(1);
        this.bankStub.getAccountBalance(request, new StreamObserver<AccountBalance>() {
            @Override
            public void onNext(AccountBalance accountBalance) {
                logger.info("Async account balance received: {}", accountBalance);
                try {
                    // This case must pass but actually this test must fail
                    Assertions.assertEquals(99, accountBalance.getBalance());
                }finally {
                    latch.countDown();
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                logger.info("Async account balance received completed");
            }
        });
        latch.await();
    }
}
