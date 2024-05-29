package com.denystry.bankapp.dto;

import com.denystry.bankapp.entity.Account;
import com.denystry.bankapp.entity.Customer;

import java.util.List;

public record CustomerDTO(Long id, String name, String email, Integer age, List<Account> accounts,String password,String phone)  {

    public CustomerDTO(String email, Integer age, List<Account> accounts,String password,String phone) {
        this(0L, "null", email, age, accounts,password,phone);
    }
    public CustomerDTO(Customer customer) {
        this(customer.getId(), customer.getName(), customer.getEmail(), customer.getAge(), customer.getAccounts(), customer.getPassword(), customer.getPhone());
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
