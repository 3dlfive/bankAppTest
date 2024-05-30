package com.denystry.bankapp.facada;

import com.denystry.bankapp.dto.RespAndReq.CustomerRequest;
import com.denystry.bankapp.dto.RespAndReq.CustomerResponse;
import com.denystry.bankapp.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerFacade {
    @Autowired
    private ModelMapper modelMapper;

    public Customer toEntity(CustomerRequest dto) {
        return modelMapper.map(dto, Customer.class);
    }

    public CustomerResponse toDto(Customer entity) {
        return modelMapper.map(entity, CustomerResponse.class);
    }
}