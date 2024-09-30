package com.bk.sec06.requesthandlers;

import com.bk.models.sec06.AccountBalance;
import com.bk.models.sec06.DepositRequest;
import com.bk.sec06.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DepositRequestHandler implements StreamObserver<DepositRequest> {

    private static final Logger log = LoggerFactory.getLogger(DepositRequestHandler.class);
    private final StreamObserver<AccountBalance> responseObserver;
    private int accountNumber;

    public DepositRequestHandler(StreamObserver<AccountBalance> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(DepositRequest depositRequest) {
        switch (depositRequest.getRequestCase())
        {
            case ACCOUNT_NUMBER -> accountNumber = depositRequest.getAccountNumber();
            case MONEY -> AccountRepository.addAmount(this.accountNumber,depositRequest.getMoney().getAmount());
        }
    }

    @Override
    public void onError(Throwable throwable) {
        // Mid cancel from client
        log.info("Client Error {}", throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        var accountBalance = AccountBalance.newBuilder().setAccountNumber(AccountRepository.getBalance(this.accountNumber)).build();
        this.responseObserver.onNext(accountBalance);
        this.responseObserver.onCompleted();
    }
}
