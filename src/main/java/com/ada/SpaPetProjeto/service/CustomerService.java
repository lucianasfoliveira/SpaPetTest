package com.ada.SpaPetProjeto.service;

import com.ada.SpaPetProjeto.controller.dto.CustomerRequest;
import com.ada.SpaPetProjeto.controller.dto.CustomerResponse;
import com.ada.SpaPetProjeto.controller.exception.PasswordValidationError;
import com.ada.SpaPetProjeto.model.Customer;
import com.ada.SpaPetProjeto.repository.CustomerRepository;
import com.ada.SpaPetProjeto.utils.CustomerConvert;
import com.ada.SpaPetProjeto.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse saveCustomer(CustomerRequest customerRequest)  throws PasswordValidationError {
        Customer customer = CustomerConvert.toEntity(customerRequest);
        String encodePassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodePassword);

        if(!Validator.passwordValidate(customer.getPassword())) throw new PasswordValidationError("Senha deve seguir o padrao");
        Customer customerEntity = customerRepository.save(customer);
        return CustomerConvert.toResponse(customerEntity);
    }

    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return CustomerConvert.toResponseList(customers);
    }

    public CustomerResponse getCustomerById(Integer id) {
        Optional<Customer> customerResponse = customerRepository.findById(id);
        return customerResponse.map(CustomerConvert::toResponse)
                .orElse(null);
    }

    public CustomerResponse updateCustomer(Integer id, CustomerRequest customerRequest ){
        Customer customer = CustomerConvert.toEntity(customerRequest);
        customer.setId(id);
        return CustomerConvert.toResponse(customerRepository.save(customer));
    }
    public void deleteCustomer(Integer id){
        Customer customer = customerRepository.findById(id).orElseThrow();
        customerRepository.delete(customer);
    }


}

