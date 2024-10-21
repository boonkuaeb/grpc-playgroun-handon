package com.bk.common;

import com.bk.sec06.BankService;
import com.bk.sec06.TransferService;

public class Demo {
    public static void main(String[] args) {


        GrpcServer.create(new BankService(),new TransferService())
                .start()
                .await();
    }


}
