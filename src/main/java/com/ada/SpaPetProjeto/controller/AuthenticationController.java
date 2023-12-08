package com.ada.SpaPetProjeto.controller;

import com.ada.SpaPetProjeto.controller.dto.LoginRequest;
import com.ada.SpaPetProjeto.controller.dto.TokenResponse;
import com.ada.SpaPetProjeto.infra.security.TokenService;
import com.ada.SpaPetProjeto.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest){

        var authenticate = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );

        var authentication = authenticationManager.authenticate(authenticate);
        var token = tokenService.tokenGenerate((Customer) authentication.getPrincipal());

        return ResponseEntity.ok().body(new TokenResponse(token));
    }
}





