package com.denystry.bankapp.service;

import com.denystry.bankapp.dao.CustomerLoacalDAOImpl;
import com.denystry.bankapp.dto.AccountDTO;
import com.denystry.bankapp.dto.CustomerDTO;
import com.denystry.bankapp.entity.Account;
import com.denystry.bankapp.entity.Customer;
import com.denystry.bankapp.helpers.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomerService {
    private final CustomerLoacalDAOImpl customerDao;
    private final IdGenerator idGenerator;

    @Autowired
    public CustomerService(CustomerLoacalDAOImpl customerDao, IdGenerator idGenerator) {
        this.customerDao = customerDao;
        this.idGenerator = new IdGenerator();
    }

    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = new Customer(idGenerator.generateCustomerId(), customerDTO.name(), customerDTO.email(), customerDTO.age());
        customer = customerDao.save(customer);
        return new CustomerDTO(customer);
    }

    public boolean delete(CustomerDTO customerDTO) {

        return false;
    }

    public void deleteAll(List<CustomerDTO> customerDTOs) {
        return;
    }

    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerDao.findAll();
        return customers.stream().map(CustomerDTO::new).collect(Collectors.toList());
    }

    public boolean deleteById(long id) {
        return customerDao.deleteById(id);
    }

    public CustomerDTO getOne(long id) {
        Customer customer = customerDao.getOne(id);
        return new CustomerDTO(customer);
    }
    public Customer getOneCustomer(long id) {
        Customer customer = customerDao.getOne(id);
        return customer;
    }




    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Customer customer = customerDao.updateCustomer(customerId, new Customer(customerDTO.name(),customerDTO.email(),customerDTO.age()));
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getEmail(),customer.getAge(),customer.getAccounts());
    }

    public void deleteCustomer(Long customerId) {
        deleteById(customerId);
    }

    public AccountDTO createAccountForCustomer(Long customerId, AccountDTO accountDTO) {


        Account newAccount = new Account(idGenerator.generateAccountId(),accountDTO.currency(),accountDTO.balance(),customerId);

        System.out.println("Service " + newAccount);
        Account accountForCustomer = customerDao.createAccountForCustomer(customerId,newAccount);
        return new AccountDTO(accountForCustomer.getId(),accountForCustomer.getNumber(),accountForCustomer.getCurrency(),accountForCustomer.getBalance(),accountForCustomer.getCustomer());
    }

    public void deleteAccountFromCustomer(Long customerId, Long accountId) {
        customerDao.deleteAccountFromCustomer(customerId,accountId);
    }

    public boolean deposit(String accountNumber, double amount) {
        return customerDao.deposit(accountNumber,amount);
    }

    public boolean withdraw(String accountNumber, double amount) {
        return customerDao.withdraw(accountNumber,amount);
    }

    public boolean transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        return customerDao.transfer(fromAccountNumber,toAccountNumber,amount);
    }
}
