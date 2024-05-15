package com.denystry.bankapp.controllers;

import com.denystry.bankapp.dto.CustomerDTO;
import com.denystry.bankapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ThymeleafController {
    private final CustomerService customerService;

    @Autowired
    public ThymeleafController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("/all")
    public String helloPage(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers"; // Имя шаблона, который должен быть отображен (hello.html)
    }
    @GetMapping("/hello")
    public String helloPage() {
        return "hello"; // Имя шаблона, который должен быть отображен (hello.html)
    }

    @GetMapping("/customer/{customerId}")
    public String viewCustomerDetails(@PathVariable Long customerId, Model model) {
        CustomerDTO customer = customerService.getOne(customerId);
        if (customer == null) {
            return "error";
        }
        model.addAttribute("customer", customer);
        return "customer-details"; // Имя шаблона для отображения информации о клиенте
    }
}
