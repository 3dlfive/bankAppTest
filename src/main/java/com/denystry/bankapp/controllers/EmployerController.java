package com.denystry.bankapp.controllers;

import com.denystry.bankapp.entity.Employer;
import com.denystry.bankapp.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employers")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @GetMapping
    public List<Employer> getAllEmployers() {
        return employerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employer> getEmployerById(@PathVariable Long id) {
        return employerService.findById(id)
                .map(employer -> ResponseEntity.ok().body(employer))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Employer createEmployer(@RequestBody Employer employer) {
        return employerService.save(employer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employer> updateEmployer(@PathVariable Long id, @RequestBody Employer employerDetails) {
        return employerService.findById(id)
                .map(employer -> {
                    employer.setName(employerDetails.getName());
                    employer.setAddress(employerDetails.getAddress());
                    Employer updatedEmployer = employerService.save(employer);
                    return ResponseEntity.ok().body(updatedEmployer);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployer(@PathVariable Long id) {
        return employerService.findById(id)
                .map(employer -> {
                    employerService.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
