package com.denystry.bankapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5000"})
public class ClientForwardController {
//    @GetMapping(value = "/**/{path:[^\\.]*}")
@GetMapping(value = "/{path:^(?!api\\/).*}")
public String forward() {
        return "forward:/";
    }
}
