package com.bk.sec06.requesthandlers;

import com.bk.models.sec06.AccountBalance;
import com.bk.models.sec06.TransferRequest;
import com.bk.models.sec06.TransferResponse;
import com.bk.models.sec06.TransferStatus;
import com.bk.sec06.repository.AccountRepository;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransferRequestHandler implements StreamObserver<TransferRequest> {

    private static final Logger logger = LoggerFactory.getLogger(TransferRequestHandler.class);
    private final StreamObserver<TransferResponse> responseObserver;

    public TransferRequestHandler(StreamObserver<TransferResponse> responseObserver) {
        this.responseObserver = responseObserver;
    }


    @Override
    public void onNext(TransferRequest transferRequest) {
        var status = this.transfer(transferRequest);
        var response = TransferResponse.newBuilder()
                .setFromAccount(this.newAccountBalance(transferRequest.getFromAccount()))
                .setToAccount(this.newAccountBalance(transferRequest.getToAccount()))
                .setStatus(status)
                .build();
        this.responseObserver.onNext(response);
    }

    @Override
    public void onError(Throwable throwable) {
        logger.info("client error {}",throwable.getMessage());
        this.responseObserver.onError(throwable);
    }

    @Override
    public void onCompleted() {
        logger.info("Transfer request completed");
        this.responseObserver.onCompleted();
    }


    private TransferStatus transfer(TransferRequest request)
    {
        var amount = request.getAmount();
        var fromAccount  = request.getFromAccount();
        var toAccount  = request.getToAccount();
        var status = TransferStatus.REJECTED;

        if (AccountRepository.getBalance(fromAccount) >= amount
        && fromAccount != toAccount
        ) {
            AccountRepository.deductAmount(fromAccount, amount);
            AccountRepository.addAmount(toAccount, amount);
            status=TransferStatus.COMPLETED;
        }
        return status;
    }

    private AccountBalance newAccountBalance(int accountNumber)
    {
        return AccountBalance.newBuilder()
                .setAccountNumber(accountNumber)
                .setBalance(AccountRepository.getBalance(accountNumber))
                .build();
    }
}
