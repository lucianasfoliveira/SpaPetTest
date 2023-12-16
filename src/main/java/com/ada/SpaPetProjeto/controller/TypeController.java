package com.ada.SpaPetProjeto.controller;

import com.ada.SpaPetProjeto.controller.dto.TypeRequest;
import com.ada.SpaPetProjeto.controller.dto.TypeResponse;
import com.ada.SpaPetProjeto.model.Type;
import com.ada.SpaPetProjeto.service.TypeService;
import com.ada.SpaPetProjeto.utils.TypeConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    TypeService typeService;

    @PostMapping("/add")
    public ResponseEntity<TypeResponse> createType(@RequestBody TypeRequest typeRequest) {
        Type savedType = typeService.saveType(typeRequest);
        TypeResponse response = TypeConvert.toResponse(savedType);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("list")
    public ResponseEntity<List<TypeResponse>> getAllTypes() {
        List<TypeResponse> typeResponses = typeService.getAllTypes();
        return new ResponseEntity<>(typeResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeResponse> getTypeById(@PathVariable Integer id) {
        Type type = typeService.getTypeById(id);
        if (type == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TypeResponse response = TypeConvert.toResponse(type);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

        @PutMapping("/{id}")
        public ResponseEntity<TypeResponse> updateType(@PathVariable Integer id, @RequestBody TypeRequest typeRequest) {
        Type updatedType = typeService.updateType(id, typeRequest);
        TypeResponse response = TypeConvert.toResponse(updatedType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteType(@PathVariable Integer id) {
        typeService.deleteType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
