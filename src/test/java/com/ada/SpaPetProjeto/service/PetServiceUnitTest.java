package com.ada.SpaPetProjeto.service;

import com.ada.SpaPetProjeto.controller.dto.PetRequest;
import com.ada.SpaPetProjeto.controller.dto.PetResponse;
import com.ada.SpaPetProjeto.model.Pet;
import com.ada.SpaPetProjeto.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class PetServiceUnitTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    private Pet pet;

    @BeforeEach
    public void setup() {
        pet = new Pet();

        Mockito.when(petRepository.findAll()).thenReturn(Arrays.asList(pet));
    }

    @Test
    public void test_save_pet() {
        Mockito.when(petRepository.save(Mockito.any(Pet.class))).thenReturn(pet);
        Pet savedPet = petService.savePet(new PetRequest("teste","teste", 2));
        Assertions.assertNotNull(savedPet);
    }

    @Test
    public void test_all_pets() {
        List<PetResponse> petResponses = petService.getAllPets();
        Assertions.assertFalse(petResponses.isEmpty());
    }

    @Test
    public void test_get_pet_by_id_success() {
        Mockito.when(petRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(pet));

        PetResponse petResponse = petService.getPetById(1);
        Assertions.assertNotNull(petResponse);
    }

    @Test
    public void test_get_pet_by_id_null() {
        PetResponse petResponse = petService.getPetById(null);
        Assertions.assertNull(petResponse);
    }

    @Test
    public void test_update_pet() {
        Pet updatedPet = new Pet();
        updatedPet.setId(1);

        Mockito.when(petRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(pet));
        Mockito.when(petRepository.save(Mockito.any(Pet.class))).thenReturn(updatedPet);

        PetResponse petResponse = petService.updatePet(1, new PetRequest("teste", "teste", 2));

        Assertions.assertNotNull(petResponse);
    }

    @Test
    public void test_delete_pet() {
        Mockito.when(petRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Pet()));
        Assertions.assertDoesNotThrow(() -> petService.deletePet(1));
    }
}
