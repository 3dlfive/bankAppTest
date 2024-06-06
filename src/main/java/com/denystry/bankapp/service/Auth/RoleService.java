package com.denystry.bankapp.service.Auth;


import com.denystry.bankapp.dao.RoleRepository;
import com.denystry.bankapp.entity.auth.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}