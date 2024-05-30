package com.denystry.bankapp.facada;

import com.denystry.bankapp.dto.RespAndReq.EmployerRequest;
import com.denystry.bankapp.dto.RespAndReq.EmployerResponse;
import com.denystry.bankapp.entity.Employer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployerFacade {
    @Autowired
    private ModelMapper modelMapper;

    public Employer toEntity(EmployerRequest dto) {
        return modelMapper.map(dto, Employer.class);
    }

    public EmployerResponse toDto(Employer entity) {
        return modelMapper.map(entity, EmployerResponse.class);
    }
}
