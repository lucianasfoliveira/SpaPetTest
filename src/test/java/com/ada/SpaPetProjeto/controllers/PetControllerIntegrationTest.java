package com.ada.SpaPetProjeto.controllers;

import com.ada.SpaPetProjeto.controller.dto.PetRequest;
import com.ada.SpaPetProjeto.controller.dto.PetResponse;
import com.ada.SpaPetProjeto.model.Pet;
import com.ada.SpaPetProjeto.service.PetService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class PetControllerIntegrationTest {

    @MockBean
    private PetService petService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_add_pet() throws Exception {
        PetRequest petRequest = new PetRequest("Buddy", "Dog", null);

        Pet addPet = new Pet();
        addPet.setId(1);
        addPet.setName("Buddy");
        addPet.setType("Dog");

        Mockito.when(petService.savePet(Mockito.any(PetRequest.class)))
                .thenReturn(addPet);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/pet/add")
                        .content("""
                            {
                                "name": "Buddy",
                                "type": "Dog"
                            }
                            """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        );
    }

    @Test
    public void test_get_pet_by_id() throws Exception {
        PetResponse retrievedPet = new PetResponse();
        retrievedPet.setId(1);
        retrievedPet.setName("Buddy");
        retrievedPet.setType("Dog");

        Mockito.when(petService.getPetById(1))
                .thenReturn(retrievedPet);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/pet/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        );
    }

    @Test
    public void test_get_all_pets() throws Exception {
        List<PetResponse> petList = Arrays.asList(
                new PetResponse("Thor", "dog", 1),
                new PetResponse("Luf" , "cat", 2)
        );

        Mockito.when(petService.getAllPets())
                .thenReturn(petList);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/pet/list")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Thor")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].type").value("dog")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].id").value(2)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].name").value("Luf")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].type").value("cat")
        );
    }

    @Test
    public void not_possible_to_validate_pet_without_name() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/pet/add")
                        .content("""
                                {
                                    "type" : (dog),
                                    "id": [1],
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void not_possible_to_validate_pet_without_type() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/pet/add")
                        .content("""
                                {
                                    "name" : (Thor),
                                    "id": [1],
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void not_possible_to_validate_pet_without_id() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/pet/add")
                        .content("""
                                {
                                    "name": (Thor),
                                    "type" : (dog),
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void update_pet_by_id() throws Exception {
        int petId = 1;

        mockMvc.perform(
                MockMvcRequestBuilders.put("/pet/{id}", petId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "name": "Thor",
                                    "type": "dog",
                                    "id": "1"
                                }
                                """)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete_pet_by_id() throws Exception {
        int petId = 1;
        Mockito.doNothing().when(petService).deletePet(1);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/pet/{id}", petId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
