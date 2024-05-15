package com.denystry.bankapp.dao;

import com.denystry.bankapp.entity.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class AccountLocalDAOImpl implements AccountLocalDao {
    private List<Account> accounts = new ArrayList<>();

    @Override
    public Account save(Account obj) {
        accounts.add(obj);
        return obj;
    }

    @Override
    public boolean delete(Account obj) {
        return accounts.remove(obj);
    }

    @Override
    public void deleteAll(List<Account> entities) {
        accounts.removeAll(entities);
    }

    @Override
    public void saveAll(List<Account> entities) {
        accounts.addAll(entities);
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public boolean deleteById(long id) {
        return accounts.removeIf(account -> account.getId() == id);
    }

    @Override
    public Account getOne(long id) {
        for (Account account : accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    @Override
    public boolean withdraw(String accountNumber, double amount) {
        Account account = findByNumber(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findByNumber(fromAccountNumber);
        Account toAccount = findByNumber(toAccountNumber);
        if (fromAccount != null && toAccount != null && fromAccount.getBalance() >= amount) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        Account account = findByNumber(accountNumber);
        if (account != null) {
            account.setBalance(account.getBalance() + amount);
            return true;
        }
        return false;
    }
    public Account findByNumber(String accountNumber) {
        for (Account account : accounts) {
            System.out.println(accountNumber + "::::" + account);
            if (account.getNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
