package com.bk.sec06.requesthandlers;

import com.bk.models.sec06.AccountBalance;
import com.bk.models.sec06.DepositRequest;
import com.bk.sec06.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DepositRequestHandler implements StreamObserver<DepositRequest> {
    private static final Logger logger = LoggerFactory.getLogger(DepositRequestHandler.class);
    private final StreamObserver<AccountBalance> responseObserver;
    private int accountNumber;


    public DepositRequestHandler(StreamObserver<AccountBalance> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(DepositRequest depositRequest) {
        logger.info("Received deposit request {}",depositRequest.getRequestCase());
        switch (depositRequest.getRequestCase()){
            case ACCOUNT_NUMBER:
                 this.accountNumber = depositRequest.getAccountNumber();
                 logger.info("Account number: " + this.accountNumber);
                break;
            case MONEY :
                AccountRepository.addAmount(this.accountNumber,depositRequest.getMoney().getAmount());
                break;
        }
    }

    @Override
    public void onError(Throwable throwable) {
        logger.info("client error {}",throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        logger.info("client completed {} ,{}",this.accountNumber,AccountRepository.getBalance(this.accountNumber));
        var accountBalance = AccountBalance.newBuilder()
                .setAccountNumber(this.accountNumber)
                .setBalance(AccountRepository.getBalance(this.accountNumber))
                .build();
        this.responseObserver.onNext(accountBalance);
        this.responseObserver.onCompleted();
    }
}
