package com.ada.SpaPetProjeto.controller;

import com.ada.SpaPetProjeto.controller.dto.CustomerRequest;
import com.ada.SpaPetProjeto.controller.dto.CustomerResponse;
import com.ada.SpaPetProjeto.controller.exception.PasswordValidationError;
import com.ada.SpaPetProjeto.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;
    @PostMapping("/add")
    public ResponseEntity<CustomerResponse> saveCustomer(
            @Valid @RequestBody CustomerRequest customerRequest
    ) throws PasswordValidationError {
        CustomerResponse customer =  customerService.saveCustomer(customerRequest);
        return ResponseEntity.created(URI.create("/customer/add"+customer.getId())).body(customer);
    }

    @GetMapping("/list")
    public ResponseEntity<?> listCustomers() {
        List<CustomerResponse> customerResponses = customerService.getAllCustomers();

        if (customerResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Essa lista está vazia");
        } else {
            return ResponseEntity.ok(customerResponses);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Integer id) {
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        if (customerResponse != null) {
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Integer id, @RequestBody CustomerRequest customerRequest){
        return ResponseEntity.ok(customerService.updateCustomer(id, customerRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id){customerService.deleteCustomer(id);}


}
