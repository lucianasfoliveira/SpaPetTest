package com.ada.SpaPetProjeto.service;

import com.ada.SpaPetProjeto.controller.dto.CustomerRequest;
import com.ada.SpaPetProjeto.controller.dto.CustomerResponse;
import com.ada.SpaPetProjeto.model.Customer;
import com.ada.SpaPetProjeto.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
        customer = new Customer(/* Set necessary data */);

        Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
    }



    @Test
    public void test_save_customer(){


    }

    @Test
    public void test_all_customer(){

        List<CustomerResponse> customerResponses = customerService.getAllCustomers();

        Assertions.assertFalse(customerResponses.isEmpty());
    }



    @Test
    public void test_get_customer_by_id_success() {
        // Configurar o customerRepository para retornar um Optional não vazio
        Mockito.when(customerRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(customer));

        // Chamar o método getCustomerById
        CustomerResponse customerResponse = customerService.getCustomerById(1);

        // Verificar se o resultado é não nulo e os dados estão corretos
        Assertions.assertNotNull(customerResponse);
        // Adicione verificações específicas conforme necessário
    }

    @Test
    public void test_get_customer_by_id_null() {
        // Chamar o método getCustomerById com ID nulo
        CustomerResponse customerResponse = customerService.getCustomerById(null);

        // Verificar se o resultado é nulo ou outra resposta adequada para entradas inválidas
        Assertions.assertNull(customerResponse);
    }

    @Test
    public void test_update_customer(){

// Configurar o customerRepository para retornar um Customer atualizado
        Customer updatedCustomer = new Customer();

        // Configurar o customerRepository para retornar um Customer atualizado
       // Customer updatedCustomer = new Customer(/* Set dados atualizados */);

        Mockito.when(customerRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(customer));
        Mockito.when(customerRepository.save(Mockito.any(Customer.class)))
                .thenReturn(updatedCustomer);

        // Chamar o método updateCustomer
        CustomerResponse customerResponse = customerService.updateCustomer(1, new CustomerRequest(
                "Jéssica",
                "test@test.com",
                "password"));

        // Verificar se o resultado é não nulo e os dados estão corretos
        Assertions.assertNotNull(customerResponse);
        // Adicione verificações específicas conforme necessário

    }

    @Test
    public void test_delete_customer(){
        // Configurar o customerRepository para retornar um Optional contendo um Customer existente
        Mockito.when(customerRepository.findById(Mockito.anyInt()))
                .thenReturn(Optional.of(new Customer()));

        // Chamar o método deleteCustomer
        Assertions.assertDoesNotThrow(() -> customerService.deleteCustomer(1));
        // Verificar se a exclusão foi bem-sucedida (pode verificar se o método delete foi chamado)
    }


}
