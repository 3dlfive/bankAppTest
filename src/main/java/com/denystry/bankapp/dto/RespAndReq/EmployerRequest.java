package com.denystry.bankapp.dto.RespAndReq;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployerRequest {
    @NotNull
    @Size(min = 3, message = "Company name must be at least 3 characters long")
    private String name;

    @NotNull
    @Size(min = 3, message = "Address must be at least 3 characters long")
    private String address;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
