package com.ada.SpaPetProjeto.service;


import com.ada.SpaPetProjeto.controller.dto.OrderRequest;
import com.ada.SpaPetProjeto.controller.dto.OrderResponse;
import com.ada.SpaPetProjeto.model.Customer;
import com.ada.SpaPetProjeto.model.Order;
import com.ada.SpaPetProjeto.model.Type;
import com.ada.SpaPetProjeto.repository.CustomerRepository;
import com.ada.SpaPetProjeto.repository.OrderRepository;
import com.ada.SpaPetProjeto.repository.TypeRepository;
import com.ada.SpaPetProjeto.utils.OrderConvert;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final TypeRepository typeRepository;
    private final CustomerRepository customerRepository;


    public OrderResponse saveOrder(OrderRequest orderRequest){
        Customer customer = customerRepository.findById(orderRequest.getCustomerId()).get();
        List<Type> types = new ArrayList<>();
        List<Integer> typesId = orderRequest.getServiceId();
        for(Integer id: typesId){
            Type type = typeRepository.findById(id).get();
            types.add(type);
        }
        Order order = OrderConvert.toEntity(orderRequest, customer, types);
        return OrderConvert.toResponse(orderRepository.save(order));
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return OrderConvert.toResponseList(orders);
    }

    public OrderResponse getOrderById(Integer id) {
        Optional<Order> orderResponse = orderRepository.findById(id);
        return orderResponse.map(OrderConvert::toResponse)
                .orElse(null);
    }


    public Order updateOrder(Integer orderId, OrderRequest updatedOrderRequest) {
        Order existingOrder = orderRepository.findById(orderId).orElse(null);

        if (existingOrder != null) {
            existingOrder.setTotalPrice(updatedOrderRequest.getTotalPrice());
            if (updatedOrderRequest.getServiceId() != null && !updatedOrderRequest.getServiceId().isEmpty()) {
                List<Type> updatedServices = new ArrayList<>();
                for (Integer typeId : updatedOrderRequest.getServiceId()) {
                    Type service = typeRepository.findById(typeId).orElse(null);
                    if (service != null) {
                        updatedServices.add(service);
                    }
                }
                existingOrder.setServiceId(updatedServices);
            }
            return orderRepository.save(existingOrder);
        }

        return null;
    }

    public boolean deleteOrder(Integer orderId) {
        Order existingOrder = orderRepository.findById(orderId).orElse(null);
        if (existingOrder != null) {
            orderRepository.delete(existingOrder);
            return true;
        }
        return false;
    }

}