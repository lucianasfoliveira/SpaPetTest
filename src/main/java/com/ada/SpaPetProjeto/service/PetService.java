package com.ada.SpaPetProjeto.service;

import com.ada.SpaPetProjeto.controller.dto.PetRequest;
import com.ada.SpaPetProjeto.controller.dto.PetResponse;
import com.ada.SpaPetProjeto.model.Pet;
import com.ada.SpaPetProjeto.repository.PetRepository;
import com.ada.SpaPetProjeto.utils.PetConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    @Autowired
    PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Pet savePet(PetRequest petRequest) {
        Pet pet = PetConvert.toEntity(petRequest);
        return petRepository.save(pet);
    }

    public List<PetResponse> getAllPets() {
        List<Pet> pets = petRepository.findAll();
        return PetConvert.toResponseList(pets);
    }

    public PetResponse getPetById(Integer id) {
        Optional<Pet> petResponse = petRepository.findById(id);
        return petResponse.map(PetConvert::toResponse)
                .orElse(null);
    }

    public PetResponse updatePet(Integer id, PetRequest petRequest ){
        Pet pet = PetConvert.toEntity(petRequest);
        pet.setId(id);
        return PetConvert.toResponse(petRepository.save(pet));
    }
    public void deletePet(Integer id){
        Pet pet = petRepository.findById(id).orElseThrow();
        petRepository.delete(pet);
    }


}
