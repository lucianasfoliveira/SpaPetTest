package com.ada.SpaPetProjeto.service;


import com.ada.SpaPetProjeto.controller.dto.TypeRequest;
import com.ada.SpaPetProjeto.controller.dto.TypeResponse;
import com.ada.SpaPetProjeto.model.Type;
import com.ada.SpaPetProjeto.repository.TypeRepository;
import com.ada.SpaPetProjeto.utils.TypeConvert;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    public Type saveType(TypeRequest typeRequest) {
        Type type = TypeConvert.toEntity(typeRequest);
        return typeRepository.save(type);
    }

    public List<TypeResponse> getAllTypes() {
        List<Type> types = typeRepository.findAll();
        return TypeConvert.toResponseList(types);
    }

    public Type getTypeById(Integer id) {
        return typeRepository.findById(id).orElse(null);
    }


    public Type updateType(Integer id, TypeRequest typeRequest) {
        Type existingType = typeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Tipo não encontrado com o ID: " + id));

        existingType.setServicePrice(typeRequest.getServicePrice());
        existingType.setServiceName(typeRequest.getServiceName());
        existingType.setServiceDescription(typeRequest.getServiceDescription());


        return typeRepository.save(existingType);
    }

    public void deleteType(Integer id) {
        Type type = typeRepository.findById(id).orElseThrow();
        type.setActive(false);
        typeRepository.delete(type);
    }
}



