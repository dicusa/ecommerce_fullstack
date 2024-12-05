package com.example.ecommerce_backend.services;

import com.example.ecommerce_backend.enums.OrderStatus;
import com.example.ecommerce_backend.models.*;
import com.example.ecommerce_backend.repositories.CartRepository;
import com.example.ecommerce_backend.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    public Order createOrder(Cart cart) {
        double totalPrice = calculateTotalprice(cart);

        System.out.println("=====Total"+totalPrice);
        Set<OrderItem> orderItems=populateOrderItems(cart.getItems());
        // Create Order
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setItems(orderItems);
        order.setTotalPrice(totalPrice);
        order.setStatus(OrderStatus.CREATED);

        // Save Order
        orderRepository.save(order);

        // Clear the cart after checkout
        cartRepository.delete(cart);

        // Return response
        return order;
    }

    private Set<OrderItem> populateOrderItems(List<CartItem> items) {
        return items.stream().map(cartItem -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            return orderItem;
        }).collect(Collectors.toSet());
    }

    private double calculateTotalprice(Cart cart) {
        return cart.getItems().stream()
                .mapToDouble(item -> {
                    Product product = item.getProduct();
                    if (product == null) {
                        throw new IllegalStateException("Product not found for CartItem ID: " + item.getId());
                    }
                    return item.getQuantity() * product.getPrice();
                })
                .sum();
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orders= orderRepository.findAll();
        return orders.stream()
                .map(this::convertToOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto updateOrderStatus(UUID orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        order.setStatus(status);
        return convertToOrderDto(orderRepository.save(order));
    }

    public OrderDto convertToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setUser(order.getUser().getId().toString());  // Assuming you want to set user ID
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setStatus(order.getStatus());
        System.out.println("OrderItems"+order.getItems());
        // Convert OrderItems to OrderItemDtos
        Set<OrderItemDto> orderItemDtos = order.getItems().stream()
                .map(this::convertToOrderItemDto)
                .collect(Collectors.toSet());

        orderDto.setItems(orderItemDtos);

        return orderDto;
    }

    private OrderItemDto convertToOrderItemDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();

        orderItemDto.setId(orderItem.getId());
        orderItemDto.setProductCode(orderItem.getProduct().getId().toString());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(orderItem.getProduct().getPrice());

        return orderItemDto;
    }
}
