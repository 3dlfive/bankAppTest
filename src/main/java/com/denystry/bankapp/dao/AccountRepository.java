package com.denystry.bankapp.dao;

import com.denystry.bankapp.entity.Account;
import com.denystry.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.number = :number")
    Account findByNumber(@Param("number") String number);
}
