package com.example.ecommerce_backend.models;

import java.util.List;
import java.util.UUID;

public class CartDTO {

    private UUID id;
    private List<CartItemDTO> items;

    public double getTotalCartPrice() {
        return totalCartPrice;
    }

    public void setTotalCartPrice(double totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }

    private double totalCartPrice;
    // Getters and setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }
}

