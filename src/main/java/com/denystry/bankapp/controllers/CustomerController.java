package com.denystry.bankapp.controllers;

import com.denystry.bankapp.dto.AccountDTO;
import com.denystry.bankapp.dto.CustomerDTO;
import com.denystry.bankapp.dto.RespAndReq.AccountRequest;
import com.denystry.bankapp.dto.RespAndReq.AccountResponse;
import com.denystry.bankapp.dto.RespAndReq.CustomerRequest;
import com.denystry.bankapp.dto.RespAndReq.CustomerResponse;
import com.denystry.bankapp.entity.Customer;
import com.denystry.bankapp.entity.Employer;
import com.denystry.bankapp.facada.AccountFacade;
import com.denystry.bankapp.facada.CustomerFacade;
import com.denystry.bankapp.facada.EmployerFacade;
import com.denystry.bankapp.service.CustomerService;
import com.denystry.bankapp.service.CustomerServiceJpa;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
@Slf4j
public class CustomerController {
    private final CustomerServiceJpa customerService;

    @Autowired
    private EmployerFacade employerFacade;
    @Autowired
    private CustomerFacade customeFacade;
    @Autowired
    private AccountFacade accountFacade;
    @Autowired
    public CustomerController(CustomerServiceJpa customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long customerId) {
        log.info("Getting customer with ID: {}", customerId);
        Optional<Customer> customerOpt = customerService.getOne(customerId);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            CustomerResponse customerResponse = customeFacade.toDto(customer);
            log.info("Found customer: {}", customerResponse);
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        } else {
            log.info("Customer with ID: {} not found", customerId);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        log.info("Getting all customers, page: {}, size: {}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        List<CustomerResponse> customers = customerService.findAll(pageable)
                .stream()
                .map(customeFacade::toDto)
                .toList();
        log.info("Found {} customers", customers.size());
        return ResponseEntity.ok(customers);
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customer) {
        log.info("Creating customer: {}", customer);
        Customer savedCustomer = customerService.save(customeFacade.toEntity(customer));
        CustomerResponse response = customeFacade.toDto(savedCustomer);
        log.info("Customer created: {}", response);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long customerId, @Valid @RequestBody CustomerRequest customer) {
        log.info("Updating customer with ID: {}, data: {}", customerId, customer);
        Customer updatedCustomer = customerService.updateCustomer(customerId, customeFacade.toEntity(customer));
        CustomerResponse response = customeFacade.toDto(updatedCustomer);
        log.info("Customer updated: {}", response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        log.info("Deleting customer with ID: {}", customerId);
        customerService.deleteCustomer(customerId);
        log.info("Customer with ID: {} deleted", customerId);
    }

    @PostMapping("/{customerId}/accounts")
    public ResponseEntity<AccountResponse> createAccountForCustomer(@PathVariable Long customerId, @Valid @RequestBody AccountRequest accountDTO) {
        log.info("Creating account for customer with ID: {}, account data: {}", customerId, accountDTO);
        AccountResponse response = accountFacade.toDto(customerService.createAccountForCustomer(customerId, accountFacade.toEntity(accountDTO)));
        log.info("Account created for customer with ID: {}, account: {}", customerId, response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{customerId}/accounts/{accountId}")
    public void deleteAccountFromCustomer(@PathVariable Long customerId, @PathVariable Long accountId) {
        log.info("Deleting account with ID: {} from customer with ID: {}", accountId, customerId);
        customerService.deleteAccountFromCustomer(customerId, accountId);
        log.info("Account with ID: {} deleted from customer with ID: {}", accountId, customerId);
    }
}
