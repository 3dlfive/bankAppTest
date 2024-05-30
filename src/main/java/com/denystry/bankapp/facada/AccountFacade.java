package com.denystry.bankapp.facada;

import com.denystry.bankapp.dto.RespAndReq.AccountRequest;
import com.denystry.bankapp.dto.RespAndReq.AccountResponse;
import com.denystry.bankapp.entity.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountFacade {
    @Autowired
    private ModelMapper modelMapper;

    public Account toEntity(AccountRequest dto) {
        return modelMapper.map(dto, Account.class);
    }

    public AccountResponse toDto(Account entity) {
        return modelMapper.map(entity, AccountResponse.class);
    }
}
