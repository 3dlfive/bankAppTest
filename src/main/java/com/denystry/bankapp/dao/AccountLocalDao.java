package com.denystry.bankapp.dao;

import com.denystry.bankapp.entity.Account;
import org.springframework.web.bind.annotation.RequestParam;

public interface AccountLocalDao extends DAO<Account> {
    boolean withdraw(String accountNumber,double amount);
    boolean transfer( String fromAccountNumber,String toAccountNumber,double amount);
    boolean deposit(String accountNumber,double amount);
    Account findByNumber(String accountNumber);
}
