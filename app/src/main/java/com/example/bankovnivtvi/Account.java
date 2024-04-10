package com.example.bankovnivtvi;

public class Account {

    private long id;
    private long userId;
    private String accountType;
    private double balance;

    public Account(long id, long userId, String accountType, double balance) {
        this.id = id;
        this.userId = userId;
        this.accountType = accountType;
        this.balance = balance;
    }

    // Gettery pro vlastnosti účtu
    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }
}

