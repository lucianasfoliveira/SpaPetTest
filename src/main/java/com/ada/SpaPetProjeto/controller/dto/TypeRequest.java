package com.ada.SpaPetProjeto.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeRequest {
    private Integer id;
    private double servicePrice;
    private String serviceName;
    private String serviceDescription;
}
