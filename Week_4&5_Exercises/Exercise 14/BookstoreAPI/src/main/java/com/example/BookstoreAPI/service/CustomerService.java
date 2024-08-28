package com.example.BookstoreAPI.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.example.BookstoreAPI.Repo.CustomerRepo;
import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.models.Customer;

@Service
public class CustomerService {
    @Autowired
    public CustomerRepo repo;

    @Autowired
    public ModelMapper modelMapper;

    public ResponseEntity<List<CustomerDTO>> getCustomers(){
        List<CustomerDTO> customers = repo.findAll().stream()
        .map(customerList -> userToDto(customerList))
        .collect(Collectors.toList());
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    public ResponseEntity<Void> addCustomer(CustomerDTO customerDTO){
        Customer customer=dtoToUser(customerDTO);
        customer.setId((int) (repo.findAll().size() + 1));
        repo.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<Void> registerCustomer(String name, String email, String address){
        Customer customer = new Customer();
        customer.setId((int) (repo.findAll().size() + 1));
        customer.setName(name);
        customer.setEmail(email);
        repo.save(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public CustomerDTO userToDto(Customer customer){
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

    public Customer dtoToUser(CustomerDTO customerDTO){
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        return customer;
    }
}
