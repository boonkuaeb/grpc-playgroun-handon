package com.bk.sec06;

import com.bk.common.AbstractChannelTest;
import com.bk.common.GrpcServer;
import com.bk.models.sec06.BankServiceGrpc;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class AbstractTest extends AbstractChannelTest {
    private final GrpcServer grpcServer = GrpcServer.create(new BankService());
    protected BankServiceGrpc.BankServiceBlockingStub blockingStub;

    @BeforeAll
    public void setUp() throws Exception {
        this.grpcServer.start();
        this.blockingStub = BankServiceGrpc.newBlockingStub(channel);
    }

    @AfterAll
    public void stop() throws Exception {
        this.grpcServer.stop();
    }
}
