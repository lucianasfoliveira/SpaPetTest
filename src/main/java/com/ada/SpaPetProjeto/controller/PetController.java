package com.ada.SpaPetProjeto.controller;

import com.ada.SpaPetProjeto.controller.dto.PetRequest;
import com.ada.SpaPetProjeto.controller.dto.PetResponse;
import com.ada.SpaPetProjeto.model.Pet;
import com.ada.SpaPetProjeto.service.PetService;
import com.ada.SpaPetProjeto.utils.PetConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @PostMapping("/add")
    public ResponseEntity<PetResponse> savePet(@RequestBody PetRequest petRequest) {
        Pet savedPet = petService.savePet(petRequest);
        PetResponse petResponse = PetConvert.toResponse(savedPet);
        return new ResponseEntity<>(petResponse, HttpStatus.CREATED);
    }


    @GetMapping("/list")
    public ResponseEntity<?> listPets() {
        List<PetResponse> petResponses = petService.getAllPets();

        if (petResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Essa lista está vazia");
        } else {
            return ResponseEntity.ok(petResponses);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPetById(@PathVariable Integer id) {
        PetResponse petResponse = petService.getPetById(id);
        if (petResponse != null) {
            return new ResponseEntity<>(petResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pet não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> updatePet(
            @PathVariable Integer id, @RequestBody PetRequest petRequest){
        return ResponseEntity.ok(petService.updatePet(id, petRequest));
    }

    @DeleteMapping("/{id}")
    public void deletePet(@PathVariable Integer id){petService.deletePet(id);}


}
