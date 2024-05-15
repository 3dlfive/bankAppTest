package com.denystry.bankapp.dto;

import com.denystry.bankapp.entity.Account;
import com.denystry.bankapp.entity.Customer;

import java.util.List;

public record CustomerDTO(Long id, String name, String email, Integer age, List<Account> accounts)  {

    public CustomerDTO(String email, Integer age, List<Account> accounts) {
        this(0L, "null", email, age, accounts);
    }
    public CustomerDTO(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getEmail(), customer.getAge(), customer.getAccounts());
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
