package com.ada.SpaPetProjeto.repository;

import com.ada.SpaPetProjeto.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository <Pet, Integer> {
}
