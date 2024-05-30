package com.denystry.bankapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Account extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number= UUID.randomUUID().toString();

    @Enumerated(EnumType.STRING)
    private Currency currency;
    private Double balance = 0.0;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    public Account(Currency currency, Customer customer) {
        this();
        this.currency = currency;
        this.customer = customer;
    }

    public Account(Currency currency, Double balance, Customer customer) {
        this();
        this.id = id;
        this.currency = currency;
        this.balance = balance;
        this.customer = customer;
    }

}
