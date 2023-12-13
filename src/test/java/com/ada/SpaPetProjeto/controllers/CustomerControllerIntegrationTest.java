package com.ada.SpaPetProjeto.controllers;

import com.ada.SpaPetProjeto.controller.dto.CustomerResponse;
import com.ada.SpaPetProjeto.model.Customer;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIntegrationTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void not_possible_to_validate_customer_whithout_name() throws Exception {
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
    public void not_possible_to_validate_customer_whithout_email() throws Exception {
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
    public void not_possible_to_validate_customer_whithout_password() throws Exception {
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
}

//    @Test
//    public void customer_with_all_the_information_should_be_validated() throws Exception {
//        mockMvc.perform(
//                        MockMvcRequestBuilders.post("/customer/add")
//                                .content("""
//                            {
//                                "name": "João",
//                                "email" : "one@teste.com",
//                                "password": "senha123"
//                            }
//                            """)
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .accept(MediaType.APPLICATION_JSON)
//                )
//                .andExpect(MockMvcResultMatchers.status().isCreated())
//                .andExpect(jsonPath("$.id").exists())  // Verifica se o ID está presente na resposta
//                .andExpect(jsonPath("$.name").value("João"))  // Verifica se o nome está correto na resposta
//                .andExpect(jsonPath("$.email").value("one@teste.com"));  // Verifica se o e-mail está correto na resposta
//    }
//
//    @Test
//    public void deve_listar_cliente_existentes() throws Exception {
//        // Defina clientes existentes no seu repositório mockado ou simulado para validar a listagem.
//        List<CustomerResponse> customers = Arrays.asList(
//                new CustomerResponse(1, "João", "joao@teste.com"),
//                new CustomerResponse(2, "Maria", "maria@teste.com")
//        );
//        Mockito.when(customerService.getAllCustomers()).thenReturn(customers);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/customer/list")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andExpect(MockMvcResultMatchers.status().isOk());
//    }
//}



//    @Test
//    public void customer_whith_all_the_informations_should_be_validate() throws Exception {
//        Mockito.doAnswer(invocationOnMock -> {
//            Customer customer = (Customer) invocationOnMock.getArgument(0);
//            customer.setId(123l);
//            return null;
//        }).when(useCase).create(Mockito.any());
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/customers")
//                        .content("""
//                                {
//                                    "name": "Will",
//                                    "document":"000"
//                                }
//                                """)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(
//                MockMvcResultHandlers.print()
//        ).andExpect(//andExpect é um assert dessa forma de teste
//                MockMvcResultMatchers.status().is2xxSuccessful()
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$.id")
//                        .exists()
//        );
//    }

//    @Test
//    public void nao_deve_ser_possivel_cadastrar_cliente_sem_informar_o_nome() throws Exception {
//        // O teste garante que ao receber um cliente sem a informação de nome a
//        // aplicação irá retornar o status code 400
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/customers")
//                        .content("""
//                                {
//                                    "name": "",
//                                    "document": "0000",
//                                    "email" : ["one@teste.com"],
//                                    "telephone": ["999999"],
//                                    "birthDate": "2020-01-01"
//                                }
//                                """)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(
//                MockMvcResultHandlers.print()
//        ).andExpect(//andExpect é um assert dessa forma de teste
//                MockMvcResultMatchers.status().isBadRequest()
//        );
//    }
//
//    @Test
//    public void deve_listar_cliente_existentes() throws Exception {
//        Customer customer = new Customer();
//        customer.setId(123l);
//        customer.setName("Will");
//        customer.setDocument("0000");
//        Mockito.when(useCase.list()).thenReturn(List.of(customer));
//
//        // O teste garante que ao receber um cliente sem a informação de nome a
//        // aplicação irá retornar o status code 400
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/customers")
//                        .accept(MediaType.APPLICATION_JSON)
//        ).andDo(
//                MockMvcResultHandlers.print()
//        ).andExpect(//andExpect é um assert dessa forma de teste
//                MockMvcResultMatchers.status().is2xxSuccessful()
//        ).andExpect(
//                MockMvcResultMatchers.jsonPath("$[0].name")
//                        .value("Will")
//        );
//    }
//
//}
//

