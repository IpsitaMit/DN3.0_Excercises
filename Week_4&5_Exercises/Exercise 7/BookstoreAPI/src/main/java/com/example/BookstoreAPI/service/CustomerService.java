package com.example.BookstoreAPI.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.models.Customer;

@Service
public class CustomerService {
    @Autowired
    public List<Customer> customerList;

    @Autowired
    public ModelMapper modelMapper;

    public List<CustomerDTO> getCustomers(){
        List<CustomerDTO> customers = customerList.stream()
        .map(customerList -> userToDto(customerList))
        .collect(Collectors.toList());
        return customers;
    }

    public void addCustomer(CustomerDTO customerDTO){
        Customer customer=dtoToUser(customerDTO);
        customer.setId((int) (customerList.size() + 1));
        customerList.add(customer);
    }

    public void registerCustomer(String name, String email, String address){
        Customer customer = new Customer();
        customer.setId((int) (customerList.size() + 1));
        customer.setName(name);
        customer.setEmail(email);
        customerList.add(customer);
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
