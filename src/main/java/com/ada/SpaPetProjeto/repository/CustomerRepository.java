package com.ada.SpaPetProjeto.repository;

import com.ada.SpaPetProjeto.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    UserDetails findByEmail(String email);
}
