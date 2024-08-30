package com.bk.sec03;

public record JsonPersonDto(
        String lastName,
    int age,
        String email,
        boolean employed,
        double salary,
        long bankAccountNumber,
        int balance
) {

}
