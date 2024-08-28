package com.example.BookstoreAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerConroller {

    @Autowired
    public CustomerService service;

    @GetMapping
    public List<CustomerDTO> getCustomers(){
        return service.getCustomers();
    }

    @PostMapping
    public void addCustomer(@RequestBody CustomerDTO customerDTO){
        service.addCustomer(customerDTO);
    }

    @PostMapping("/register")
    public void registerCustomer(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String address) {
        service.registerCustomer(name, email, address);
    }
}
