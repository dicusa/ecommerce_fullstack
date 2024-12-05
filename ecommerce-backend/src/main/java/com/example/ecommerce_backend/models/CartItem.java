package com.example.ecommerce_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    @ManyToOne
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonBackReference
    private Cart cart;

    private int quantity;

    public CartItem() {
    }

    public CartItem(Product product, Cart cart, int quantity) {
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
    }

    // Getters and setters
}
