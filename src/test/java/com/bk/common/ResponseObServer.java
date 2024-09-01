package com.bk.common;

/**
 * Test Utility
 */
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ResponseObServer<T> implements StreamObserver<T> {
    private static final Logger logger = LoggerFactory.getLogger(ResponseObServer.class);
    private final List<T> list = new ArrayList<>();
    private final CountDownLatch latch;
    private Throwable throwable;

    public ResponseObServer(int countDown) {
        this.latch = new CountDownLatch(countDown);
    }

    public static<T> ResponseObServer<T> create()
    {
        return new ResponseObServer<>(1);
    }

    public static<T> ResponseObServer<T> countDown(int countDown)
    {
        return new ResponseObServer<>(countDown);
    }

    @Override
    public void onNext(T t) {
        logger.info("received item: {}", t);
        this.list.add(t);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.info("received error: {}", throwable.getMessage());
        this.throwable = throwable;
        this.latch.countDown();
    }

    @Override
    public void onCompleted() {
        logger.info("completed... ");
        latch.countDown();
    }

    public void await() {
        try {
            this.latch.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public List<T> getList() {
        return this.list;
    }
    public Throwable getThrowable() {
        return this.throwable;
    }
}
