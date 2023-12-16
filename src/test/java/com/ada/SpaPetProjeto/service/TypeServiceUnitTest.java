package com.ada.SpaPetProjeto.service;

import com.ada.SpaPetProjeto.controller.dto.TypeRequest;
import com.ada.SpaPetProjeto.controller.dto.TypeResponse;
import com.ada.SpaPetProjeto.model.Type;
import com.ada.SpaPetProjeto.repository.TypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class TypeServiceUnitTest {


    @Mock
    private TypeRepository typeRepository;

    @InjectMocks
    private TypeService typeService;

    @BeforeEach
    public void setup() {
        Type type = new Type();
        type.setId(1);
        type.setServiceName("Banho");
        type.setServiceDescription("Banho completo");
        type.setServicePrice(50.0);

        Mockito.when(typeRepository.findAll()).thenReturn(Collections.singletonList(type));
        Mockito.when(typeRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(type));
        Mockito.when(typeRepository.save(Mockito.any(Type.class))).thenReturn(type);
    }

    @Test
    public void test_save_type() {
        TypeRequest typeRequest = new TypeRequest();
        typeRequest.setServiceName("Tosa");
        typeRequest.setServiceDescription("Tosa Higienica");
        typeRequest.setServicePrice(60.0);

        Mockito.when(typeRepository.save(Mockito.any(Type.class)))
                .thenAnswer(invocation -> {
                    Type argument = invocation.getArgument(0);
                    argument.setServiceName("Tosa");
                    return argument;
                });

        Type savedType = typeService.saveType(typeRequest);

        Assertions.assertNotNull(savedType);
        Assertions.assertEquals("Tosa", savedType.getServiceName());
        Mockito.verify(typeRepository, Mockito.times(1)).save(Mockito.any(Type.class));
    }

    @Test
    public void test_get_all_types() {
        List<TypeResponse> types = typeService.getAllTypes();

        Assertions.assertFalse(types.isEmpty());
        Assertions.assertEquals(1, types.size());
    }

    @Test
    public void test_get_type_by_id_success() {
        Type retrievedType = typeService.getTypeById(1);

        Assertions.assertNotNull(retrievedType);
        Assertions.assertEquals("Banho", retrievedType.getServiceName());
    }

    @Test
    public void test_get_type_by_id_null() {
        Type retrievedType = typeService.getTypeById(null);

        Assertions.assertNull(retrievedType);
    }

    @Test
    public void test_update_type() {
        TypeRequest typeRequest = new TypeRequest();
        typeRequest.setServiceName("Banho2");
        typeRequest.setServiceDescription("Banho com hidratacao");
        typeRequest.setServicePrice(70.0);

        Type updatedType = typeService.updateType(1, typeRequest);

        Assertions.assertNotNull(updatedType);
        Assertions.assertEquals("Banho2", updatedType.getServiceName());
    }

    @Test
    public void test_update_type_existing_type() {
        Integer typeId = 1;
        TypeRequest typeRequest = new TypeRequest(/* set your request details */);

        Type existingType = new Type(/* set your existing type details */);
        Mockito.when(typeRepository.findById(typeId)).thenReturn(Optional.of(existingType));

        Type updatedType = typeService.updateType(typeId, typeRequest);

        Assertions.assertNotNull(updatedType);
    }

    @Test
    public void test_update_type_non_existing_type() {
        Integer typeId = 1;
        TypeRequest typeRequest = new TypeRequest();

        Mockito.when(typeRepository.findById(typeId)).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            typeService.updateType(typeId, typeRequest);
        });
    }
    @Test
    public void test_delete_type() {
        Assertions.assertDoesNotThrow(() -> typeService.deleteType(1));
        Mockito.verify(typeRepository, Mockito.times(1)).delete(Mockito.any(Type.class));
    }
}