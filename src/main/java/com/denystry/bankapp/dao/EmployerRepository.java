package com.denystry.bankapp.dao;

import com.denystry.bankapp.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerRepository extends JpaRepository<Employer, Long> {
}
