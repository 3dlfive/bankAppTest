package com.denystry.bankapp.service;

import com.denystry.bankapp.dao.AccountRepository;
import com.denystry.bankapp.dao.CustomerRepository;
import com.denystry.bankapp.dao.EmployerRepository;
import com.denystry.bankapp.dto.AccountDTO;
import com.denystry.bankapp.dto.CustomerDTO;
import com.denystry.bankapp.dto.RespAndReq.CustomerRequest;
import com.denystry.bankapp.entity.Account;
import com.denystry.bankapp.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceJpa {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public void delete(Customer customer) {
         customerRepository.delete(customer);
    }

    public void deleteAll(List<Customer> customers) {
        customerRepository.deleteAll(customers);
    }
    @Transactional
    public List<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll();
    }

    public void deleteById(long id) {
         customerRepository.deleteById(id);
    }

    public Optional<Customer> getOne(long id) {
        return customerRepository.findById(id);
    }
    @Transactional
    public Optional<Customer> getOneCustomer(long id) {
        return customerRepository.findById(id);
    }




    public Customer updateCustomer(Long customerId, Customer customer) {
        Customer costomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        costomer.setName(customer.getName());
        costomer.setAge(customer.getAge());
        costomer.setAccounts(customer.getAccounts());
        costomer.setPhone(customer.getPhone());
        costomer.setPassword(customer.getPassword());
        costomer.setEmployers(customer.getEmployers());
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    public Account createAccountForCustomer(Long customerId, Account account) {
        Customer customer = customerRepository.getOne(customerId);

        Account account1 = new Account(account.getCurrency(), account.getBalance(), customer);
        List<Account> accounts = customer.getAccounts();accounts.add(account1);
        customer.setAccounts(accounts);
        customerRepository.save(customer);

        return account1;
    }

    @Transactional
    public void deleteAccountFromCustomer(Long customerId, Long accountId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        customerOptional.ifPresentOrElse((customer) -> {
            Account accountToRemove = null;
            for (Account account : customer.getAccounts()) {
                if (accountId.equals(account.getId())) {
                    accountToRemove = account;
                    break;
                }
            }
            if (accountToRemove != null) {
                customer.getAccounts().remove(accountToRemove);
                accountRepository.delete(accountToRemove);
                customerRepository.save(customer);
            } else {
                throw new RuntimeException("Account not found for customer");
            }
        }, () -> {
            throw new RuntimeException("Customer not found");
        });
    }

    @Transactional
    public boolean deposit(String accountNumber, double amount) {
        Account acc = accountRepository.findByNumber(accountNumber);
        if (acc != null) {
            try {
                double newBalance = acc.getBalance() + amount;
                acc.setBalance(newBalance);
                accountRepository.save(acc);
                return true;
            } catch (Exception e) {
                // Log the exception or handle it appropriately
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }


    }

    public boolean withdraw(String accountNumber, double amount) {
        Account acc = accountRepository.findByNumber(accountNumber);
        if (acc != null) {
            try {
                double newBalance = acc.getBalance() - amount;
                acc.setBalance(newBalance);
                accountRepository.save(acc);
                return true;
            } catch (Exception e) {
                // Log the exception or handle it appropriately
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }


    @Transactional
    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        // Retrieve sender and receiver accounts
        Account fromAccount = accountRepository.findByNumber(fromAccountNumber);
        Account toAccount = accountRepository.findByNumber(toAccountNumber);

        // Ensure both accounts exist
        if (fromAccount != null && toAccount != null) {
            // Ensure sender has sufficient balance
            if (fromAccount.getBalance() >= amount) {
                try {
                    // Deduct amount from sender's account
                    fromAccount.setBalance(fromAccount.getBalance() - amount);

                    // Add amount to receiver's account
                    toAccount.setBalance(toAccount.getBalance() + amount);

                    // Save both accounts
                    accountRepository.save(fromAccount);
                    accountRepository.save(toAccount);

                    // Transaction successful
                    return true;
                } catch (Exception e) {
                    // Log or handle the exception
                    e.printStackTrace();
                    return false;
                }
            } else {
                // Insufficient balance in sender's account
                return false;
            }
        } else {
            // Either sender or receiver account does not exist
            return false;
        }
    }
}
