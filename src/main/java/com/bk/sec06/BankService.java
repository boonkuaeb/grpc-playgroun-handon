package com.bk.sec06;

import com.bk.models.sec06.AccountBalance;
import com.bk.models.sec06.AllAccountsResponse;
import com.bk.models.sec06.BalanceCheckRequest;
import com.bk.models.sec06.BankServiceGrpc;
import com.bk.sec06.repository.AccountRepository;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
