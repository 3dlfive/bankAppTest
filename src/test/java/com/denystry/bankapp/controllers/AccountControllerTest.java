package com.denystry.bankapp.controllers;

import com.denystry.bankapp.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AccountControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDepositToAccount_Success() {
        String accountNumber = "123456";
        double amount = 100.0;

        when(customerService.deposit(accountNumber, amount)).thenReturn(true);

        ResponseEntity<String> response = accountController.depositToAccount(accountNumber, amount);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deposited " + amount + " to account " + accountNumber, response.getBody());
    }

    @Test
    public void testDepositToAccount_Failure() {
        String accountNumber = "123456";
        double amount = 100.0;

        when(customerService.deposit(accountNumber, amount)).thenReturn(false);

        ResponseEntity<String> response = accountController.depositToAccount(accountNumber, amount);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to deposit to account " + accountNumber, response.getBody());
    }

    @Test
    public void testWithdrawFromAccount_Success() {
        String accountNumber = "123456";
        double amount = 50.0;

        when(customerService.withdraw(accountNumber, amount)).thenReturn(true);

        ResponseEntity<String> response = accountController.withdrawFromAccount(accountNumber, amount);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Withdrawn " + amount + " from account " + accountNumber, response.getBody());
    }

    @Test
    public void testWithdrawFromAccount_Failure() {
        String accountNumber = "123456";
        double amount = 50.0;

        when(customerService.withdraw(accountNumber, amount)).thenReturn(false);

        ResponseEntity<String> response = accountController.withdrawFromAccount(accountNumber, amount);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to withdraw from account " + accountNumber, response.getBody());
    }

    @Test
    public void testTransferBetweenAccounts_Success() {
        String fromAccountNumber = "123456";
        String toAccountNumber = "654321";
        double amount = 30.0;

        when(customerService.transfer(fromAccountNumber, toAccountNumber, amount)).thenReturn(true);

        ResponseEntity<String> response = accountController.transferBetweenAccounts(fromAccountNumber, toAccountNumber, amount);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Transferred " + amount + " from account " + fromAccountNumber + " to account " + toAccountNumber, response.getBody());
    }

    @Test
    public void testTransferBetweenAccounts_Failure() {
        String fromAccountNumber = "123456";
        String toAccountNumber = "654321";
        double amount = 30.0;

        when(customerService.transfer(fromAccountNumber, toAccountNumber, amount)).thenReturn(false);

        ResponseEntity<String> response = accountController.transferBetweenAccounts(fromAccountNumber, toAccountNumber, amount);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to transfer from account " + fromAccountNumber + " to account " + toAccountNumber, response.getBody());
    }
}
