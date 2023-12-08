package com.ada.SpaPetProjeto.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerRequest {
    @NotBlank()
    private String name;
    @Email
    private String email;
    private String password;
}

