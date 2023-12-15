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
        // Configurar o comportamento do petRepository para retornar o pet salvo
        Mockito.when(petRepository.save(Mockito.any(Pet.class))).thenReturn(pet);

        // Chamar o método savePet e verificar se o resultado não é nulo
        Pet savedPet = petService.savePet(new PetRequest("teste","teste", 2));
        Assertions.assertNotNull(savedPet);
        // Adicione verificações adicionais conforme necessário
    }

    @Test
    public void test_all_pets() {
        List<PetResponse> petResponses = petService.getAllPets();

        Assertions.assertFalse(petResponses.isEmpty());
    }

    @Test
    public void test_get_pet_by_id_success() {
        // Configurar o petRepository para retornar um Optional não vazio
        Mockito.when(petRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(pet));

        // Chamar o método getPetById
        PetResponse petResponse = petService.getPetById(1);

        // Verificar se o resultado é não nulo
        Assertions.assertNotNull(petResponse);
        // Adicione verificações específicas conforme necessário
    }

    @Test
    public void test_get_pet_by_id_null() {
        // Chamar o método getPetById com ID nulo
        PetResponse petResponse = petService.getPetById(null);

        // Verificar se o resultado é nulo ou outra resposta adequada para entradas inválidas
        Assertions.assertNull(petResponse);
    }

    @Test
    public void test_update_pet() {
        // Configurar o petRepository para retornar um Pet atualizado
        Pet updatedPet = new Pet(/* Set dados atualizados */);
        updatedPet.setId(1);

        Mockito.when(petRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(pet));
        Mockito.when(petRepository.save(Mockito.any(Pet.class))).thenReturn(updatedPet);

        // Chamar o método updatePet
        PetResponse petResponse = petService.updatePet(1, new PetRequest("teste", "teste", 2));

        // Verificar se o resultado é não nulo
        Assertions.assertNotNull(petResponse);
        // Adicione verificações específicas conforme necessário
    }

    @Test
    public void test_delete_pet() {
        // Configurar o petRepository para retornar um Optional contendo um Pet existente
        Mockito.when(petRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new Pet()));

        // Chamar o método deletePet
        Assertions.assertDoesNotThrow(() -> petService.deletePet(1));
        // Verificar se a exclusão foi bem-sucedida (pode verificar se o método delete foi chamado)
    }
}
