package com.ada.SpaPetProjeto.service;

import com.ada.SpaPetProjeto.controller.dto.OrderRequest;
import com.ada.SpaPetProjeto.controller.dto.OrderResponse;
import com.ada.SpaPetProjeto.model.Customer;
import com.ada.SpaPetProjeto.model.Order;
import com.ada.SpaPetProjeto.model.Type;
import com.ada.SpaPetProjeto.repository.CustomerRepository;
import com.ada.SpaPetProjeto.repository.OrderRepository;
import com.ada.SpaPetProjeto.repository.TypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(SpringExtension.class)
public class OrderServiceUnitTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private TypeRepository typeRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setup() {
        Customer mockCustomer = new Customer();
        mockCustomer.setId(1);
        mockCustomer.setName("Luciana");
        mockCustomer.setEmail("lsf@email.com");

        Order mockOrder = new Order();
        mockOrder.setId(1);
        mockOrder.setTotalPrice(50.0);

        Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(mockCustomer));
        Mockito.when(typeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Type()));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(mockOrder);
    }

 //   @Test
//    void test_save_order() {
//        OrderRequest orderRequest = new OrderRequest();
//        Customer customer = new Customer();
//        Type type = new Type();
//
//        Mockito.when(customerRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(customer));
//        Mockito.when(typeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(type));
//        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(new Order());
//
//        OrderResponse orderResponse = orderService.saveOrder(orderRequest);
//
//        Assertions.assertNotNull(orderResponse);
//    }

    @Test
    public void test_get_all_orders_empty() {
        Mockito.when(orderRepository.findAll()).thenReturn(new ArrayList<>());

        List<OrderResponse> orderResponses = orderService.getAllOrders();

        Assertions.assertNotNull(orderResponses);
        Assertions.assertTrue(orderResponses.isEmpty());
    }

    @Test
    public void test_get_order_by_id_not_found() {
        Integer orderId = 1;

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        OrderResponse orderResponse = orderService.getOrderById(orderId);
        Assertions.assertNull(orderResponse);
    }

    @Test
    public void test_update_order() {
        Integer orderId = 1;
        OrderRequest updatedOrderRequest = new OrderRequest();
        Order existingOrder = new Order();

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        Mockito.when(typeRepository.findById(anyInt())).thenReturn(Optional.of(new Type()));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(existingOrder);

        Order updatedOrder = orderService.updateOrder(orderId, updatedOrderRequest);
        Assertions.assertNotNull(updatedOrder);
    }

    @Test
    public void test_update_existing_order() {
        Integer orderId = 1;
        OrderRequest updatedOrderRequest = new OrderRequest();
        updatedOrderRequest.setTotalPrice(75.0);

        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setTotalPrice(50.0);

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        Mockito.when(typeRepository.findById(anyInt())).thenReturn(Optional.of(new Type()));
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenReturn(existingOrder);

        Order updatedOrder = orderService.updateOrder(orderId, updatedOrderRequest);

        Assertions.assertNotNull(updatedOrder);
        Assertions.assertEquals(75.0, updatedOrder.getTotalPrice());
    }

    @Test
    public void test_update_order_not_found() {
        Integer orderId = 1;
        OrderRequest updatedOrderRequest = new OrderRequest();

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        Order updatedOrder = orderService.updateOrder(orderId, updatedOrderRequest);
        Assertions.assertNull(updatedOrder);
    }

    @Test
    public void test_update_order_with_services() {
        Integer orderId = 1;
        OrderRequest updatedOrderRequest = new OrderRequest();
        updatedOrderRequest.setTotalPrice(75.0);
        updatedOrderRequest.setServiceId(Arrays.asList(1, 2));

        Order existingOrder = new Order();
        existingOrder.setId(orderId);
        existingOrder.setTotalPrice(50.0);

        Type mockType1 = new Type();
        mockType1.setId(1);

        Type mockType2 = new Type();
        mockType2.setId(2);

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        Mockito.when(typeRepository.findById(1)).thenReturn(Optional.of(mockType1));
        Mockito.when(typeRepository.findById(2)).thenReturn(Optional.of(mockType2));

        Order updatedOrder = orderService.updateOrder(orderId, updatedOrderRequest);

        Assertions.assertNotNull(updatedOrder);
    }

    @Test
    public void test_delete_order() {
        Integer orderId = 1;
        Order existingOrder = new Order();

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        Mockito.doNothing().when(orderRepository).delete(existingOrder);

        boolean result = orderService.deleteOrder(orderId);

        Assertions.assertTrue(result);
    }

    @Test
    public void test_delete_order_not_found() {
        Integer orderId = 1;

        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        boolean result = orderService.deleteOrder(orderId);

        Assertions.assertFalse(result);
    }
}

