package com.ada.SpaPetProjeto.repository;

import com.ada.SpaPetProjeto.model.Customer;
import com.ada.SpaPetProjeto.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
