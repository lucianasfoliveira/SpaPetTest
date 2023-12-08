package com.ada.SpaPetProjeto.controller.dto;

import com.ada.SpaPetProjeto.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponse {
    private Integer id;
    private Double totalPrice;
    private Customer customer;
    private List<Integer> serviceId;

}