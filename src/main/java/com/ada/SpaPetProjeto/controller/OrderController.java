package com.ada.SpaPetProjeto.controller;

import com.ada.SpaPetProjeto.controller.dto.OrderRequest;
import com.ada.SpaPetProjeto.controller.dto.OrderResponse;
import com.ada.SpaPetProjeto.model.Order;
import com.ada.SpaPetProjeto.service.OrderService;
import com.ada.SpaPetProjeto.utils.OrderConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody OrderRequest orderRequest){
        OrderResponse orderResponse = orderService.saveOrder(orderRequest);
        return ResponseEntity.created(URI.create("/order/"+orderResponse.getId())).body(orderResponse);
    }


    @GetMapping("/list")
    public ResponseEntity<?> getAllOrders() {
        List<OrderResponse> orderResponses = orderService.getAllOrders();
        if (orderResponses.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Essa lista está vazia");
        } else {
            return ResponseEntity.ok(orderResponses);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        OrderResponse orderResponse = orderService.getOrderById(id);
        if (orderResponse != null) {
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Pedido não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{Id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable Integer Id, @RequestBody OrderRequest updatedOrderRequest) {
        Order updatedOrder = orderService.updateOrder(Id, updatedOrderRequest);
        if (updatedOrder != null) {
            OrderResponse orderResponse = OrderConvert.toResponse(updatedOrder);
            return ResponseEntity.ok(orderResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer Id) {
        boolean deleted = orderService.deleteOrder(Id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}