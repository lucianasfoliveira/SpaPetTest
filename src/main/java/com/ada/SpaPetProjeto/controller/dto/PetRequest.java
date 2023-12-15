package com.ada.SpaPetProjeto.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PetRequest {
    @NotBlank()
    private String name;
    private String type;
    private Integer id;

}

