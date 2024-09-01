package com.bk.sec06;
import com.bk.common.ResponseObserver;
import com.bk.models.sec06.AccountBalance;
import com.bk.models.sec06.AllAccountsResponse;
import com.bk.models.sec06.BalanceCheckRequest;
import com.google.protobuf.Empty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec02UnaryAsyncClient02Test extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(Lec02UnaryAsyncClient02Test.class);

    @Test
    public void getBalanceTests() {
        var request = BalanceCheckRequest.newBuilder().setAccountNumber(1).build();
        var observer = ResponseObserver.<AccountBalance>create();
        this.bankStub.getAccountBalance(request, observer);
        observer.await();
        Assertions.assertEquals(1, observer.getList().size());
        Assertions.assertEquals(100, observer.getList().getFirst().getBalance());
        Assertions.assertNull(observer.getThrowable());
    }

    @Test
    public void allAccountTest() {
        var observer = ResponseObserver.<AllAccountsResponse>create();
        this.bankStub.getAllAccounts(Empty.getDefaultInstance(), observer);
        observer.await();
        Assertions.assertEquals(1, observer.getList().size());
        Assertions.assertEquals(10, observer.getList().getFirst().getAccountsCount());
        Assertions.assertNull(observer.getThrowable());
    }
}
