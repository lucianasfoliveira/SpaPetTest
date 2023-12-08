package com.ada.SpaPetProjeto.utils;

import com.ada.SpaPetProjeto.controller.dto.CustomerRequest;
import com.ada.SpaPetProjeto.controller.dto.CustomerResponse;
import com.ada.SpaPetProjeto.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerConvert {

    public static Customer toEntity(CustomerRequest customerDTO){ // transf dados de customer em novo objeto seguindo requisitos do Request DTO
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(customerDTO.getPassword());
        return customer;
    }

    public static CustomerResponse toResponse(Customer customer){ //// transf dados de customer em novo objeto seguindo requisitos do Response DTO, retorna customer.
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(customer.getId());
        customerResponse.setName(customer.getName());
        customerResponse.setEmail(customer.getEmail());
        return customerResponse;
    }

    public static List<CustomerResponse> toResponseList(List<Customer> customers){
        List<CustomerResponse> customerResponses = new ArrayList<>();
        return customers.stream()
                .map(CustomerConvert::toResponse)
                .collect(Collectors.toList());
    }
}
