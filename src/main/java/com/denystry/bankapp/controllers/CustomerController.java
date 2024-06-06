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


@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
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
    public  ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long customerId) {
        Optional<Customer> customerOpt = customerService.getOne(customerId);

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            CustomerResponse customerResponse = customeFacade.toDto(customer);
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//test
    // Отримати інформацію про всіх користувачів
@GetMapping
public  ResponseEntity<List<CustomerResponse>> getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size) {
    Pageable pageable = PageRequest.of(page, size);
    return ResponseEntity.ok(customerService.findAll(pageable)
            .stream()
            .map(customeFacade::toDto)
            .toList());
}


    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customer) {

        Customer save = customerService.save(customeFacade.toEntity(customer));//todo drop exception to respone
        return  ResponseEntity.ok(customeFacade.toDto(save));
    }

    // Змінити дані користувача
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable Long customerId, @Valid @RequestBody   CustomerRequest customer) {
        return ResponseEntity.ok(customeFacade.toDto(customerService.updateCustomer(customerId, customeFacade.toEntity(customer))));
    }

    // Видалити користувача
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
    }

    // Створити рахунок для конкретного користувача
    @PostMapping("/{customerId}/accounts")
    public ResponseEntity<AccountResponse> createAccountForCustomer(@PathVariable Long customerId, @Valid @RequestBody AccountRequest accountDTO) {

        return ResponseEntity.ok(accountFacade.toDto(customerService.createAccountForCustomer(customerId,accountFacade.toEntity(accountDTO))));
    }

    // Видалити рахунок у користувача
    @DeleteMapping("/{customerId}/accounts/{accountId}")
    public void deleteAccountFromCustomer(@PathVariable Long customerId, @PathVariable Long accountId) {
        customerService.deleteAccountFromCustomer(customerId, accountId);
    }


}
