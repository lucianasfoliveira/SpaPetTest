package com.ada.SpaPetProjeto.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest{
    private double totalPrice;
    private Integer customerId;
    private List<Integer> serviceId;
}
