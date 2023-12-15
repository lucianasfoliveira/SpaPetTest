package com.ada.SpaPetProjeto.controller.dto;

import com.ada.SpaPetProjeto.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Integer id;
    private Double totalPrice;
    private Customer customer;
    private List<Integer> serviceId;

}