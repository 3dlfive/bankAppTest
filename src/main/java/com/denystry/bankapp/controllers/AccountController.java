package com.denystry.bankapp.controllers;


import com.denystry.bankapp.dao.AccountLocalDAOImpl;
import com.denystry.bankapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final CustomerService customerService;


@Autowired
    public AccountController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositToAccount(@RequestParam String accountNumber, @RequestParam double amount) {

        boolean success = customerService.deposit(accountNumber, amount);
        if (success) {
            return ResponseEntity.ok("Deposited " + amount + " to account " + accountNumber);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to deposit to account " + accountNumber);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawFromAccount(@RequestParam String accountNumber, @RequestParam double amount) {

        boolean success = customerService.withdraw(accountNumber, amount);
        if (success) {
            return ResponseEntity.ok("Withdrawn " + amount + " from account " + accountNumber);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to withdraw from account " + accountNumber);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferBetweenAccounts(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber, @RequestParam double amount) {
        boolean success = customerService.transfer(fromAccountNumber, toAccountNumber, amount);
        if (success) {
            return ResponseEntity.ok("Transferred " + amount + " from account " + fromAccountNumber + " to account " + toAccountNumber);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to transfer from account " + fromAccountNumber + " to account " + toAccountNumber);
        }
    }
}