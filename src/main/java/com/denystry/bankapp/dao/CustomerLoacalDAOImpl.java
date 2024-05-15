package com.denystry.bankapp.dao;

import com.denystry.bankapp.entity.Account;
import com.denystry.bankapp.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CustomerLoacalDAOImpl implements CustomerLocalDao {
    private List<Customer> customers = new ArrayList<>();

    @Override
    public Customer save(Customer obj) {
        customers.add(obj);
        return obj;
    }

    @Override
    public boolean delete(Customer obj) {
        return customers.remove(obj);
    }

    @Override
    public void deleteAll(List<Customer> entities) {
        customers.removeAll(entities);
    }

    @Override
    public void saveAll(List<Customer> entities) {
        customers.addAll(entities);
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public boolean deleteById(long id) {
        return customers.removeIf(customer -> customer.getId() == id);
    }

    @Override
    public Customer getOne(long id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }
    @Override
    public void deleteAccountFromCustomer(Long customerId, Long accountId) {
        Customer customer = getOne(customerId);
        if (customer != null) {
            customer.getAccounts().removeIf(account -> account.getId().equals(accountId));
        }
    }

    @Override
    public Account createAccountForCustomer(Long customerId, Account account) {

        Customer customer = getOne(customerId);
        if (customer != null) {
            List<Account> list = new ArrayList<>();
            list = customer.getAccounts();
            list.add(account);
            customer.setAccounts(list);

            return account;
        }
        return null;
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {
        return null;
    }

    @Override
    public boolean deposit(String accountNumber, double amount) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getNumber().equals(accountNumber)) {
                    double currentBalance = account.getBalance();
                    account.setBalance(currentBalance + amount);
                    return true; // Deposit successful
                }
            }
        }
        return false; // Account not found
    }

    @Override
    public boolean withdraw(String accountNumber, double amount) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getNumber().equals(accountNumber)) {
                    double currentBalance = account.getBalance();
                    if (currentBalance >= amount) {
                        account.setBalance(currentBalance - amount);
                        return true; // Withdrawal successful
                    } else {
                        return false; // Insufficient funds
                    }
                }
            }
        }
        return false; // Account not found
    }

    @Override
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = null;
        Account toAccount = null;

        // Find the source and destination accounts
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getNumber().equals(fromAccountNumber)) {
                    fromAccount = account;
                }
                if (account.getNumber().equals(toAccountNumber)) {
                    toAccount = account;
                }
            }
        }

        // Check if both accounts are found
        if (fromAccount != null && toAccount != null) {
            double fromBalance = fromAccount.getBalance();
            if (fromBalance >= amount) {
                fromAccount.setBalance(fromBalance - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
                return true; // Transfer successful
            } else {
                return false; // Insufficient funds
            }
        }

        return false; // One or both accounts not found
    }
}
