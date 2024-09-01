package com.bk.sec06.repository;

import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountRepository {
    private static final Map<Integer,Integer> db = IntStream.rangeClosed(1,10)
            .boxed()
            .collect(Collectors.toConcurrentMap(
                    Function.identity(),
//                    v -> new Random().nextInt(1000000) + 1
                    v ->100
            ));

    public static Integer getBalance(int accountNumber) {
        return db.get(accountNumber);
    }

    public static Map<Integer,Integer> allBalance() {
        return Collections.unmodifiableMap(db);
    }

}
