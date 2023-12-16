package com.ada.SpaPetProjeto.controllers;

import com.ada.SpaPetProjeto.controller.dto.CustomerRequest;
import com.ada.SpaPetProjeto.controller.dto.CustomerResponse;
import com.ada.SpaPetProjeto.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc (addFilters=false)
public class CustomerControllerIntegrationTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_not_possible_to_validate_customer_without_name() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/customer/add")
                        .content("""
                                {
                                    "email" : ["teste@teste.com"],
                                    "password": ["123456"],
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void test_not_possible_to_validate_customer_without_email() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/customer/add")
                        .content("""
                                {
                                    "name": "teste",
                                    "password": "147852",
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void test_not_possible_to_validate_customer_without_password() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/customer/add")
                        .content("""
                                {
                                    "name": "teste",
                                    "email": "123456",
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void test_get_customer_by_id() throws Exception {
        CustomerResponse customer = new CustomerResponse(1, "Teste1", "teste@teste.com");
        int customerId = 1;

        Mockito.when(customerService.getCustomerById(customerId)).thenReturn(customer);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/customer/{id}", customerId)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(customer.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(customer.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(customer.getEmail()));
    }


    @Test
    public void test_get_all_customers() throws Exception {
        List<CustomerResponse> customers = Arrays.asList(
                new CustomerResponse(1, "teste1@teste.com", "123456789"),
                new CustomerResponse(2, "teste2@teste.com", "123456789")
        );
        Mockito.when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/customer/list")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void test_customer_with_all_the_information_should_be_saved() throws Exception {
        CustomerResponse savedCustomer = new CustomerResponse();
        savedCustomer.setId(1);

        Mockito.when(customerService.saveCustomer(Mockito.any(CustomerRequest.class)))
                .thenReturn(savedCustomer);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/customer/add")
                        .content("""
                                {
                                    "name": "Teste",
                                    "email":"teste@teste.com",
                                    "password":"12345678"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcResultHandlers.print()
        ).andExpect(
                MockMvcResultMatchers.status().is2xxSuccessful()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        );
    }

    @Test
    public void test_delete_customer_by_id() throws Exception {
        int customerId = 1;
        Mockito.doNothing().when(customerService).deleteCustomer(1);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/customer/{id}", customerId) // Use apenas o caminho base e inclua o ID como um parâmetro
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void test_update_customer_by_id() throws Exception {
        int customerId = 1;

        mockMvc.perform(
                MockMvcRequestBuilders.put("/customer/{id}", customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Teste",
                                    "email": "teste@teste.com",
                                    "password": "123456789"
                                }
                                """)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}





