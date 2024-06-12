package com.denystry.bankapp.dto.RespAndReq;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AccountRequest {
    private String number;

    @NotNull
    private String currency;

    @NotNull
    @Min(value = 0, message = "Balance cannot be negative")
    private Double balance;

    @NotNull
    private Long customerId;

    // Getters and Setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
