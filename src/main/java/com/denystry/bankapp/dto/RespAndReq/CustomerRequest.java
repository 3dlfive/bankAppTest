package com.denystry.bankapp.dto.RespAndReq;

import jakarta.validation.constraints.*;

public class CustomerRequest {
    @NotNull
    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;

    @NotNull
    @Email(message = "Email should be valid")
    private String email;

    @NotNull
    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    @NotNull
    private String password;

    @NotNull
    @Pattern(regexp = "\\+?\\d{10,15}", message = "Phone number should be valid")
    private String phone;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}