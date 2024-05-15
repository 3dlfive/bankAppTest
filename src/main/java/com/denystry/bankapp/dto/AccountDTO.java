package com.denystry.bankapp.dto;
import com.denystry.bankapp.entity.Currency;
import com.denystry.bankapp.entity.Customer;
import com.denystry.bankapp.helpers.IdGenerator;
public record AccountDTO(Long id,String number,Currency currency,Double balance,Long customerID) {

    public AccountDTO(Long id, String number, Currency currency, Double balance, Long customerID) {
        this.id = id;
        this.number = number;
        this.currency = currency;
        this.balance = balance;
        this.customerID = customerID;
    }

    public AccountDTO(Currency currency, Double balance, Long customerID) {
        this(0L, "0", currency, balance, customerID);
    }

    public AccountDTO(Currency currency, Double balance) {
        this(0L, "0", currency, balance, null);
    }

    public AccountDTO() {
        this(0L, "null", Currency.USD, 0D, 0L);
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", customerID=" + customerID +
                '}';
    }
}

