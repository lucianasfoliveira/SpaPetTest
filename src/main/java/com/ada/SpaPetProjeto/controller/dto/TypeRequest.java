package com.ada.SpaPetProjeto.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeRequest {
    private Integer id;
    private double servicePrice;
    private String serviceName;
    private String serviceDescription;
}
