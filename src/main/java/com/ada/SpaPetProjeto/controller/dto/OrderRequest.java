package com.ada.SpaPetProjeto.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest{
    private double totalPrice;
    private Integer customerId;
    private List<Integer> serviceId;
}
