package com.example.ecommerce_backend.models;

import java.util.UUID;

public class OrderResponse{

private UUID orderId;
private String message;
private double totalPrice;

public OrderResponse(UUID orderId, String message, double totalPrice) {
    this.orderId = orderId;
    this.message = message;
    this.totalPrice = totalPrice;
}

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
