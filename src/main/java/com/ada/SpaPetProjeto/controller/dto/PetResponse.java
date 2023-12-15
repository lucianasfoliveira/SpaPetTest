package com.ada.SpaPetProjeto.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetResponse {
    private String name;
    private String type;
    private Integer id;

}
