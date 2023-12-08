package com.ada.SpaPetProjeto.repository;

import com.ada.SpaPetProjeto.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
