package com.bk.sec06;

import com.bk.models.sec06.*;
import com.bk.sec06.repository.AccountRepository;
import com.bk.sec06.requesthandlers.DepositRequestHandler;
import com.google.common.util.concurrent.Uninterruptibles;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BankService extends BankServiceGrpc.BankServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BankService.class);

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
       var accountNumber = request.getAccountNumber();
       var balance = AccountRepository.getBalance(accountNumber);
       var accountBalance = AccountBalance.newBuilder()
               .setAccountNumber(accountNumber)
               .setBalance(balance)
               .build();
        responseObserver.onNext(accountBalance);
        responseObserver.onCompleted();

    }

    @Override
    public void getAllAccounts(Empty request, StreamObserver<AllAccountsResponse> responseObserver) {
       var accounts = AccountRepository.allBalance()
               .entrySet()
               .stream()
               .map(entry -> AccountBalance.newBuilder().setAccountNumber(entry.getKey()).setBalance(entry.getValue()).build())
               .toList();
       var response = AllAccountsResponse.newBuilder().addAllAccounts(accounts).build();
       responseObserver.onNext(response);
       responseObserver.onCompleted();
    }


    @Override
    public void withdraw(WithdrawRequest request, StreamObserver<Money> responseObserver) {
        /*
            Ideally we should do some input validation. But we are going to assume only happy path scenarios.
            Because, in gRPC, there are multiple ways to communicate the error message to the client. It has to be discussed in detail separately.
            Assumption: account # 1 - 10 & withdraw amount is multiple of $10
         */
        var accountNumber = request.getAccountNumber();
        var requestedAmount = request.getAmount();
        var accountBalance = AccountRepository.getBalance(accountNumber);
        log.info("Account No {} balance {}",accountNumber, accountBalance);

        if(requestedAmount > accountBalance){
            responseObserver.onCompleted(); // we will change it to proper error later
            return;
        }

        log.info("withDraw Amount= {}", requestedAmount);
        for (int i = 1; i <= (requestedAmount / 10); i++) {
            var money = Money.newBuilder().setAmount(10).build();
            responseObserver.onNext(money);
            log.info("money sent {}, {} of {}", money.getAmount(), (money.getAmount()*i),requestedAmount);
            AccountRepository.deductAmount(accountNumber, 10);
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
        log.info("onCompleted");
        responseObserver.onCompleted();
    }

//    @Override
//    public StreamObserver<DepositRequest> deposit(StreamObserver<AccountBalance> responseObserver) {
//        return new DepositRequestHandler(responseObserver);
//    }


    @Override
    public StreamObserver<DepositRequest> deposit(StreamObserver<AccountBalance> responseObserver) {
        return new DepositRequestHandler(responseObserver);
    }
}
