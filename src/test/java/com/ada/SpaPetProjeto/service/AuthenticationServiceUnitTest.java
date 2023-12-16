package com.ada.SpaPetProjeto.service;

import com.ada.SpaPetProjeto.model.Customer;
import com.ada.SpaPetProjeto.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;


public class AuthenticationServiceUnitTest {


    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsername_ExistingEmail() {
        String email = "existing@example.com";
        Customer customer = new Customer();
        Mockito.when(customerRepository.findByEmail(email)).thenReturn(customer);

        UserDetails userDetails = authenticationService.loadUserByUsername(email);
        Mockito.verify(customerRepository).findByEmail(email);
    }
}
