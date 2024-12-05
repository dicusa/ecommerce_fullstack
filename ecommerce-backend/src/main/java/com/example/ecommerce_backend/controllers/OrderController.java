package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.enums.OrderStatus;
import com.example.ecommerce_backend.models.OrderDto;
import com.example.ecommerce_backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @PostMapping
//    public Order createOrder(@RequestBody Order order) {
//        return orderService.createOrder(order);
//    }

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/{orderId}")
    public OrderDto updateOrderStatus(@PathVariable UUID  orderId, @RequestParam String status) {
        System.out.println("status: "+status);
        return orderService.updateOrderStatus(orderId, OrderStatus.valueOf(status.toUpperCase().trim()));
    }
}

