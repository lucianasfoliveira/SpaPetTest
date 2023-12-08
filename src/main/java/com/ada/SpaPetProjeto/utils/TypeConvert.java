package com.ada.SpaPetProjeto.utils;

import com.ada.SpaPetProjeto.controller.dto.TypeRequest;
import com.ada.SpaPetProjeto.controller.dto.TypeResponse;
import com.ada.SpaPetProjeto.model.Type;

import java.util.ArrayList;
import java.util.List;

public class TypeConvert {

    public static Type toEntity(TypeRequest typeRequest){
        Type type = new Type();
        type.setId(typeRequest.getId());
        type.setServiceName(typeRequest.getServiceName());
        type.setServiceDescription(typeRequest.getServiceDescription());
        type.setServicePrice(typeRequest.getServicePrice());
        return type;
    }

    public static TypeResponse toResponse(Type type){
        TypeResponse typeResponse = new TypeResponse();
        typeResponse.setId(type.getId());
        typeResponse.setServiceName(type.getServiceName());
        typeResponse.setServicePrice(type.getServicePrice());
        return typeResponse;
    }

    public static List<TypeResponse> toResponseList(List<Type> types){
        List<TypeResponse> typeResponses = new ArrayList<>();
        for(Type type : types){
            typeResponses.add(toResponse(type));
        }
        return typeResponses;
    }
}
