package com.example.BookstoreAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookstoreAPI.models.Customer;

@RestController
@RequestMapping("/customers")
public class CustomerConroller {

    @Autowired
    public List<Customer> customerList;

    @GetMapping("/")
    public List<Customer> getCustomers(){
        return customerList;
    }

    @PostMapping("/")
    public String addCustomer(@RequestBody Customer customer){
        customer.setId((int) (customerList.size() + 1));
        customerList.add(customer);
        return "added a customer";
    }

    @PostMapping("/register")
    public String registerCustomer(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String address) {
        Customer customer = new Customer();
        customer.setId((int) (customerList.size() + 1));
        customer.setName(name);
        customer.setEmail(email);
        customerList.add(customer);
        return "added by creating endpoint";
    }
}
