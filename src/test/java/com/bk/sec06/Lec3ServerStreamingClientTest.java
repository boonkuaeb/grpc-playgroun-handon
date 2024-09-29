package com.bk.sec06;

import com.bk.models.sec06.WithdrawRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec3ServerStreamingClientTest extends AbstractTest{
    private static final Logger log = LoggerFactory.getLogger(Lec3ServerStreamingClientTest.class);

    @Test
    public void blockingClientWithdrawTest() {
        var request = WithdrawRequest.newBuilder()
                .setAmount(20)
                .setAccountNumber(2)
                .build();

        var iterator = this.blockingStub.withdraw(request);
        int count = 0;
        while (iterator.hasNext()) {
            log.info("received money: {}", iterator.next().getAmount());
            count++;
        }

        Assertions.assertEquals(2, count);
    }

}
