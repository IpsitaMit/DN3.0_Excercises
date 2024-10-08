package com.example.BookstoreAPI.securuty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.BookstoreAPI.Repo.CustomerRepo;
import com.example.BookstoreAPI.exceptions.ResourceNotFoundException;
import com.example.BookstoreAPI.models.Customer;

@Service
public class CustomerDetailService implements UserDetailsService{

    @Autowired
    public CustomerRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer=repo.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("UserName Does no Exist"));
        return customer;
    }
    
}
