package com.denystry.bankapp.controllers;

import com.denystry.bankapp.dto.AccountDTO;
import com.denystry.bankapp.dto.CustomerDTO;
import com.denystry.bankapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
public class CustomerController {
    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public CustomerDTO getCustomer(@PathVariable Long customerId) {
        return customerService.getOne(customerId);
    }
//test
    // Отримати інформацію про всіх користувачів
    @GetMapping
    public List<CustomerDTO> getAllCustomers() {
        return customerService.findAll();
    }

    // Створити користувача
    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.save(customerDTO);
    }

    // Змінити дані користувача
    @PutMapping("/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(customerId, customerDTO);
    }

    // Видалити користувача
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    // Створити рахунок для конкретного користувача
    @PostMapping("/{customerId}/accounts")
    public AccountDTO createAccountForCustomer(@PathVariable Long customerId, @RequestBody AccountDTO accountDTO) {
        AccountDTO fixedAccount = new AccountDTO(accountDTO.currency(), accountDTO.balance(),customerId);

        return customerService.createAccountForCustomer(customerId, fixedAccount);
    }

    // Видалити рахунок у користувача
    @DeleteMapping("/{customerId}/accounts/{accountId}")
    public void deleteAccountFromCustomer(@PathVariable Long customerId, @PathVariable Long accountId) {
        customerService.deleteAccountFromCustomer(customerId, accountId);
    }


}
