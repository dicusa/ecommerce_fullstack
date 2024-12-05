package com.example.ecommerce_backend.services;

import com.example.ecommerce_backend.models.*;
import com.example.ecommerce_backend.repositories.CartRepository;
import com.example.ecommerce_backend.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CheckoutService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    public OrderResponse processOrder(CheckoutRequest checkoutRequest) {
        Cart cart = cartService.getCurrentuserCartForCheckout();
        if (cart == null) {
            throw new RuntimeException("No Cart Associated with current user");
        }
        System.out.println("=====BeforeTotal");
        // Calculate total price
        Order order=orderService.createOrder(cart);
        if (order!=null){
            return new OrderResponse(order.getId(), "Order placed successfully", order.getTotalPrice());
        }
        return null;
    }

}
