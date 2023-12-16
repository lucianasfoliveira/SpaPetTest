package com.ada.SpaPetProjeto.service;

import com.ada.SpaPetProjeto.controller.dto.CustomerRequest;
import com.ada.SpaPetProjeto.controller.dto.CustomerResponse;
import com.ada.SpaPetProjeto.model.Customer;
import com.ada.SpaPetProjeto.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class CustomerServiceUnitTest {


    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();

        Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
    }

    @Test
    public void test_save_type() {

    }

    @Test
    public void test_all_customer(){

        List<CustomerResponse> customerResponses = customerService.getAllCustomers();

        Assertions.assertFalse(customerResponses.isEmpty());
    }


    @Test
    public void test_get_customer_by_id_success() {
        Mockito.when(customerRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(customer));
        CustomerResponse customerResponse = customerService.getCustomerById(1);
        Assertions.assertNotNull(customerResponse);
    }

    @Test
    public void test_get_customer_by_id_null() {
        CustomerResponse customerResponse = customerService.getCustomerById(null);
        Assertions.assertNull(customerResponse);
    }

    @Test
    public void test_update_customer(){
        Customer updatedCustomer = new Customer();
        Mockito.when(customerRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.save(Mockito.any(Customer.class)))
                .thenReturn(updatedCustomer);

        CustomerResponse customerResponse = customerService.updateCustomer(1, new CustomerRequest(
                "Jéssica",
                "test@test.com",
                "password"));
        Assertions.assertNotNull(customerResponse);
    }

    @Test
    public void test_delete_customer(){
        Mockito.when(customerRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(new Customer()));
        Assertions.assertDoesNotThrow(() -> customerService.deleteCustomer(1));
    }

}
