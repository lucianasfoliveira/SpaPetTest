package com.ada.SpaPetProjeto.controllers;

import com.ada.SpaPetProjeto.controller.dto.OrderRequest;
import com.ada.SpaPetProjeto.controller.dto.OrderResponse;
import com.ada.SpaPetProjeto.model.Customer;
import com.ada.SpaPetProjeto.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc (addFilters=false)
public class OrderControllerIntegrationTest {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_get_order_by_id() throws Exception {
        OrderResponse order = new OrderResponse();
        order.setId(1);

        int orderId = 1;

        Mockito.when(orderService.getOrderById(orderId)).thenReturn(order);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/order/{id}", orderId)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(order.getId()));
    }

    @Test
    public void test_get_all_orders() throws Exception {

        List<OrderResponse> orderResponses = Arrays.asList(
                new OrderResponse(1, 100.0, new Customer(), Arrays.asList(1)),
                new OrderResponse(2, 150.0, new Customer(), Arrays.asList(1))
        );

        Mockito.when(orderService.getAllOrders()).thenReturn(orderResponses);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/order/list")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void test_order_with_all_the_information_should_be_save() throws Exception {
        OrderResponse orderSave = new OrderResponse();
        orderSave.setId(1);

        Mockito.when(orderService.saveOrder(Mockito.any(OrderRequest.class)))
                .thenReturn(orderSave);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/order")
                                .content("""
                                        {
                                            "totalPrice": 100.0,
                                            "customerId": 1,
                                            "serviceId": [1, 2, 3]
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void test_not_possible_to_validate_order_without_total_price() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/order")
                        .content("""
                                {
                                    "customerId" : (1),
                                    "serviceId": [123456],
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void test_not_possible_to_validate_order_without_customerId() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/order")
                        .content("""
                                {
                                    "totalPrice" : (100.0),
                                    "serviceId": [123456],
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void test_not_possible_to_validate_order_without_serviceId() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/order")
                        .content("""
                                {
                                    "totalPrice" : (100.0),
                                    "customerId" : (1),
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }


    @Test
    public void test_delete_order_by_id() throws Exception {
        int idOrder = 1;

        Mockito.when(orderService.deleteOrder(idOrder)).thenReturn(true);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/order/{id}", idOrder)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }


    @Test
    public void test_update_order_by_id() throws Exception {
        int orderId = 1;

        mockMvc.perform(
                MockMvcRequestBuilders.put("/order/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "totalPrice": 150,
                                    "customerId": 1,
                                    "serviceId": 1
                                }
                                """)
        ).andExpect(status().isBadRequest());
    }
}



