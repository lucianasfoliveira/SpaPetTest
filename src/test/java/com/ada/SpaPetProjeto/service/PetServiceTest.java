package com.ada.SpaPetProjeto.service;

import com.ada.SpaPetProjeto.controller.dto.PetRequest;
import com.ada.SpaPetProjeto.controller.dto.PetResponse;
import com.ada.SpaPetProjeto.model.Pet;
import com.ada.SpaPetProjeto.repository.PetRepository;
import com.ada.SpaPetProjeto.utils.PetConvert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSavePet() {
        PetRequest petRequest = new PetRequest("teste","teste", 2.5,5);
        Pet pet = PetConvert.toEntity(petRequest);

        when(petRepository.save(any(Pet.class))).thenReturn(pet);
        Pet savedPet = petService.savePet(petRequest);
        assertNotNull(savedPet);

    }

    @Test
    void testGetAllPets() {
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();

        when(petRepository.findAll()).thenReturn(Arrays.asList(pet1, pet2));
        List<PetResponse> petResponses = petService.getAllPets();
        assertEquals(2, petResponses.size());

    }

    @Test
    void testGetPetById() {
        Integer petId = 1;
        Pet pet = new Pet();

        when(petRepository.findById(petId)).thenReturn(Optional.of(pet));
        PetResponse petResponse = petService.getPetById(petId);
        assertNotNull(petResponse);

    }

    @Test
    void testUpdatePet() {
        Integer petId = 1;
        PetRequest petRequest = new PetRequest("teste", "teste", 2.5, 5);
        Pet updatedPet = PetConvert.toEntity(petRequest);
        updatedPet.setId(petId);

        when(petRepository.save(any(Pet.class))).thenReturn(updatedPet);
        PetResponse petResponse = petService.updatePet(petId, petRequest);
        assertNotNull(petResponse);

    }

    @Test
    void testDeletePet() {
        Integer petId = 1;
        Pet pet = new Pet();

        when(petRepository.findById(petId)).thenReturn(Optional.of(pet));
        assertDoesNotThrow(() -> petService.deletePet(petId));
        verify(petRepository, times(1)).delete(eq(pet));
    }
}
