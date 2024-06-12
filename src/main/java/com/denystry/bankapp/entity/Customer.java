package com.denystry.bankapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Customer extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String password;

    public String getPhone() {
        return phone;
    }

    private String phone;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Account> accounts = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "customer_employer",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "employer_id")
    )
    private Set<Employer> employers = new HashSet<>();
    public Customer(String name, String email, Integer age,String password,String phone) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
        this.phone = phone;
        this.accounts = new ArrayList<>();
    }

}
