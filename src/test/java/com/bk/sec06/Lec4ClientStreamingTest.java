package com.bk.sec06;

import com.bk.common.ResponseObserver;
import com.bk.models.sec06.AccountBalance;
import com.bk.models.sec06.DepositRequest;
import com.bk.models.sec06.Money;
import com.google.common.util.concurrent.Uninterruptibles;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Lec4ClientStreamingTest extends AbstractTest{

    @Test
    public void depositTest() {
        var responseObserver = ResponseObserver.<AccountBalance>create();
        var requestObserver = this.stub.deposit(responseObserver);

        // initial message - account number
        requestObserver.onNext(DepositRequest.newBuilder().setAccountNumber(5).build());

        // sending stream of money
        IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Money.newBuilder().setAmount(10).build())
                .map(m -> DepositRequest.newBuilder().setMoney(m).build())
                .forEach(requestObserver::onNext);

        // notifying the server that we are done
        requestObserver.onCompleted();

        // at this point out response observer should receive a response
        responseObserver.await();

        // assert
        Assertions.assertEquals(1, responseObserver.getList().size());
        Assertions.assertEquals(200, responseObserver.getList().getFirst().getBalance());
        Assertions.assertNull(responseObserver.getThrowable());
    }

    @Test
    public void depositMidCancelTest() {
        var responseObserver = ResponseObserver.<AccountBalance>create();
        var requestObserver = this.stub.deposit(responseObserver);

        // initial message - account number
        requestObserver.onNext(DepositRequest.newBuilder().setAccountNumber(5).build());
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        requestObserver.onError(new RuntimeException());

        responseObserver.await();

        Assertions.assertNotNull(responseObserver.getThrowable());
    }
}
