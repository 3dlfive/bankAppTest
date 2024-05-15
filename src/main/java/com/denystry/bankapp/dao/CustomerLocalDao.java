package com.denystry.bankapp.dao;

import com.denystry.bankapp.dto.AccountDTO;
import com.denystry.bankapp.dto.CustomerDTO;
import com.denystry.bankapp.entity.Account;
import com.denystry.bankapp.entity.Customer;

public interface CustomerLocalDao extends DAO<Customer> {
    void deleteAccountFromCustomer(Long customerId, Long accountId);
    Account createAccountForCustomer(Long customerId, Account account);
    Customer updateCustomer(Long customerId, Customer customer);
     boolean deposit(String accountNumber, double amount) ;

     boolean withdraw(String accountNumber, double amount) ;

     boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) ;
}
