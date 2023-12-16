package com.ada.SpaPetProjeto.controllers;


import com.ada.SpaPetProjeto.controller.dto.TypeRequest;
import com.ada.SpaPetProjeto.controller.dto.TypeResponse;
import com.ada.SpaPetProjeto.model.Type;
import com.ada.SpaPetProjeto.service.TypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
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


import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TypeControllerIntegrationTest {

    @MockBean
    private TypeService typeService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test_add_type() throws Exception {
        Type addType = new Type();
        addType.setId(1);
        addType.setServiceName("Banho");
        addType.setServicePrice(50);
        addType.setServiceDescription("Banho pet pequeno porte");

        when(typeService.saveType(Mockito.any(TypeRequest.class)))
                .thenReturn(addType);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/types/add")
                        .content("""
                                {
                                "servicePrice":50,
                                "serviceName":"Banho",
                                "serviceDescription":"banho pet pequeno porte"
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
    public void test_get_type_by_id() throws Exception {
        Type type = new Type();
        type.setId(1);
        type.setServiceDescription("Banho pet pequeno porte");
        type.setServiceName("Banho");
        type.setServicePrice(50);

        when(typeService.getTypeById(1))
                .thenReturn(type);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/types/1")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        );
    }

    @Test
    public void test_get_all_types() throws Exception {
        List<TypeResponse> typeList = Arrays.asList(
                new TypeResponse(1, "Banho", 50),
                new TypeResponse(2, "Tosa", 80)
        );

        when(typeService.getAllTypes())
                .thenReturn(typeList);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/types/list")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].serviceName").value("Banho")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].servicePrice").value(50)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].id").value(2)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].serviceName").value("Tosa")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].servicePrice").value(80)
        );
    }


    @Test
    public void not_possible_to_validate_type_without_service_name() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/types/add")
                        .content("""
                                {
                                    "servicePrice" : 50,
                                    "serviceDescription": ["Banho animal pequeno porte"],
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void not_possible_to_validate_type_without_service_price() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/types/add")
                        .content("""
                                {
                                    "serviceName" : "Banho",
                                    "serviceDescription": ["Banho animal pequeno porte"],
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void not_possible_to_validate_type_without_service_description() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/types/add")
                        .content("""
                                {
                                    "serviceName": "Banho",
                                    "servicePrice" : 50,
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }



    @Test
    public void test_delete_type() throws Exception {
        int typeId = 1;
        Mockito.doNothing().when(typeService).deleteType(typeId);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/types/{id}", typeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(typeService, Mockito.times(1)).deleteType(typeId);
    }

}


