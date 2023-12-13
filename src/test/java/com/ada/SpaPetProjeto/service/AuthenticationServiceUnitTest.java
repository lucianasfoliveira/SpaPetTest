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
        Customer customer = new Customer(); // Suponhamos que você tenha um objeto Customer aqui

        // Simular o retorno do repository para um email existente
        Mockito.when(customerRepository.findByEmail(email)).thenReturn(customer);

        // Executar o método loadUserByUsername
        UserDetails userDetails = authenticationService.loadUserByUsername(email);

        // Verificar se o UserDetails retornado corresponde ao esperado
        // Implemente a lógica para converter o Customer em UserDetails e faça a comparação aqui
        // UserDetails returnedUserDetails = ...;

        // Assertions.assertEquals(userDetails, returnedUserDetails);

        // Verifique se o método findByEmail foi chamado corretamente com o email fornecido
        Mockito.verify(customerRepository).findByEmail(email);
    }
}
