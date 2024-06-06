package com.denystry.bankapp.controllers;

import com.denystry.bankapp.dto.RespAndReq.AccountRequest;
import com.denystry.bankapp.dto.RespAndReq.AccountResponse;
import com.denystry.bankapp.dto.RespAndReq.CustomerRequest;
import com.denystry.bankapp.dto.RespAndReq.CustomerResponse;
import com.denystry.bankapp.entity.Account;
import com.denystry.bankapp.entity.Customer;
import com.denystry.bankapp.facada.CustomerFacade;
import com.denystry.bankapp.service.CustomerServiceJpa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CustomerServiceJpa customerService;

    @Mock
    private CustomerFacade customerFacade;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testGetCustomer_Success() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customerService.save(customer);
        CustomerResponse customerResponse = new CustomerResponse();
        when(customerService.getOneCustomer(customerId)).thenReturn(Optional.of(customer));
        when(customerFacade.toDto(customer)).thenReturn(customerResponse);

        ResponseEntity<CustomerResponse> response = customerController.getCustomer(customerId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(customerResponse, response.getBody());
    }

    @Test
    public void testGetCustomer_NotFound() {
        Long customerId = 1L;
        when(customerService.getOneCustomer(customerId)).thenReturn(Optional.empty());

        ResponseEntity<CustomerResponse> response = customerController.getCustomer(customerId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testDeleteCustomer() {
        Long customerId = 1L;

        doNothing().when(customerService).deleteCustomer(customerId);

        customerController.deleteCustomer(customerId);

        verify(customerService, times(1)).deleteCustomer(customerId);
    }

    @Test
    public void testDeleteAccountFromCustomer() {
        Long customerId = 1L;
        Long accountId = 2L;

        doNothing().when(customerService).deleteAccountFromCustomer(customerId, accountId);

        customerController.deleteAccountFromCustomer(customerId, accountId);

        verify(customerService, times(1)).deleteAccountFromCustomer(customerId, accountId);
    }
}
