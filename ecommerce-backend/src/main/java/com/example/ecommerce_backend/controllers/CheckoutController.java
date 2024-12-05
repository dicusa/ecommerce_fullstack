package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.models.CheckoutRequest;
import com.example.ecommerce_backend.models.OrderResponse;
import com.example.ecommerce_backend.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    @Autowired
    private  CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<String> checkout(@RequestBody CheckoutRequest checkoutRequest) {
        OrderResponse orderResponse = checkoutService.processOrder(checkoutRequest);
        if (orderResponse!=null){
            return ResponseEntity.ok(orderResponse.toString());

        }
        return ResponseEntity.ok("Exception Occured, cannot create order");

    }
}

