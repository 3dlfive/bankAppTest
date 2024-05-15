package com.denystry.bankapp.entity;

import java.util.UUID;

public class Account {
    private Long id;
    private String number;
    private Currency currency;
    private Double balance;
    private Long customer;

    public Account(Currency currency, Long customer) {
        this.id = 0L;
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.balance = 0.0;
        this.customer = customer;
    }

    public Account() {

    }

    public Account(Long id, Currency currency, Double balance, Long customer) {
        this.id = id;
        this.number = UUID.randomUUID().toString();
        this.currency = currency;
        this.balance = balance;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", customer_id=" + customer +
                '}';
    }

}
