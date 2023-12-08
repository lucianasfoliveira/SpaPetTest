package com.ada.SpaPetProjeto.utils;

import com.ada.SpaPetProjeto.controller.dto.OrderRequest;
import com.ada.SpaPetProjeto.controller.dto.OrderResponse;
import com.ada.SpaPetProjeto.model.Customer;
import com.ada.SpaPetProjeto.model.Order;
import com.ada.SpaPetProjeto.model.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderConvert {
    public static Order toEntity(OrderRequest orderRequest, Customer customer, List<Type> types){
        Order order = new Order();
        order.setTotalPrice(orderRequest.getTotalPrice());
        order.setCustomerId(customer);
        order.setServiceId(types);
        return order;
    }

    public static OrderResponse toResponse(Order order){
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setCustomer(order.getCustomerId());

        List<Integer> typeIds = order.getServiceId().stream()
                .map(Type::getId)
                .collect(Collectors.toList());

        orderResponse.setServiceId(typeIds);
        orderResponse.setTotalPrice(order.getTotalPrice());
        return orderResponse;
    }

    public static List<OrderResponse> toResponseList(List<Order> orders){
        List<OrderResponse> ordersResponse = new ArrayList<>();
        for(Order order: orders){
            ordersResponse.add(toResponse(order));
        }

        return ordersResponse;
    }
}
