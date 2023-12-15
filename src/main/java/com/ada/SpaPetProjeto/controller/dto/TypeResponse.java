package com.ada.SpaPetProjeto.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class TypeResponse {
        private Integer id;
        private String serviceName;
        private double servicePrice;

    }

