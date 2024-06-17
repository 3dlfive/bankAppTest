package com.denystry.bankapp.controllers;

import com.denystry.bankapp.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@Slf4j
public class AccountController {

    private final CustomerService customerService;

    @Autowired
    public AccountController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositToAccount(@RequestParam String accountNumber, @RequestParam double amount) {
        log.info("Depositing {} to account {}", amount, accountNumber);
        boolean success = customerService.deposit(accountNumber, amount);
        if (success) {
            log.info("Deposited {} to account {}", amount, accountNumber);
            return ResponseEntity.ok("Deposited " + amount + " to account " + accountNumber);
        } else {
            log.warn("Failed to deposit {} to account {}", amount, accountNumber);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to deposit to account " + accountNumber);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawFromAccount(@RequestParam String accountNumber, @RequestParam double amount) {
        log.info("Withdrawing {} from account {}", amount, accountNumber);
        boolean success = customerService.withdraw(accountNumber, amount);
        if (success) {
            log.info("Withdrawn {} from account {}", amount, accountNumber);
            return ResponseEntity.ok("Withdrawn " + amount + " from account " + accountNumber);
        } else {
            log.warn("Failed to withdraw {} from account {}", amount, accountNumber);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to withdraw from account " + accountNumber);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferBetweenAccounts(@RequestParam String fromAccountNumber, @RequestParam String toAccountNumber, @RequestParam double amount) {
        log.info("Transferring {} from account {} to account {}", amount, fromAccountNumber, toAccountNumber);
        boolean success = customerService.transfer(fromAccountNumber, toAccountNumber, amount);
        if (success) {
            log.info("Transferred {} from account {} to account {}", amount, fromAccountNumber, toAccountNumber);
            return ResponseEntity.ok("Transferred " + amount + " from account " + fromAccountNumber + " to account " + toAccountNumber);
        } else {
            log.warn("Failed to transfer {} from account {} to account {}", amount, fromAccountNumber, toAccountNumber);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to transfer from account " + fromAccountNumber + " to account " + toAccountNumber);
        }
    }
}
