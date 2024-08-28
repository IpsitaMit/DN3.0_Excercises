package com.example.BookstoreAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerConroller {

    @Autowired
    public CustomerService service;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomers(){
        return service.getCustomers();
    }

    @PostMapping
    public ResponseEntity<Void> addCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        return service.addCustomer(customerDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerCustomer(@Valid @RequestParam String name,
    @Valid @RequestParam String email,
    @Valid @RequestParam String address) {
        return service.registerCustomer(name, email, address);
    }
}
