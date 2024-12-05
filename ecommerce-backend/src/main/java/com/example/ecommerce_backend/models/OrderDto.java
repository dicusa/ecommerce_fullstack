package com.example.ecommerce_backend.models;

import com.example.ecommerce_backend.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class OrderDto {

    private UUID id;

    private String user;

    private Set<OrderItemDto> items;

    private double totalPrice;
    private LocalDateTime orderDate;

    private OrderStatus status;

    public OrderDto() {
    }
// Getters and Setters


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Set<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(Set<OrderItemDto> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}

