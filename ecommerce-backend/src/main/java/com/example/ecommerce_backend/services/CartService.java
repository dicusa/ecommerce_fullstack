package com.example.ecommerce_backend.services;

import com.example.ecommerce_backend.models.*;
import com.example.ecommerce_backend.repositories.CartItemRepository;
import com.example.ecommerce_backend.repositories.CartRepository;
import com.example.ecommerce_backend.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    public CartDTO addToCart(Long cartId, Long productId, int quantity) {

        Cart cart = getCurrentuserCart();

        // Check if the item already exists in the cart
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            // If item exists, update the quantity
            existingItem.get().setQuantity(existingItem.get().getQuantity() + quantity);
        } else {
            // If item doesn't exist, add new item
            Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
            CartItem newItem = new CartItem();
            newItem.setProduct(product);
            newItem.setCart(cart);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
        }
        Cart save = cartRepository.save(cart);
        return toCartDTO(save);
        }


    public CartDTO removeItemFromCart(Long productId) {
        Cart cart = getCurrentuserCart();
        CartItem itemToRemove = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));
        cart.getItems().remove(itemToRemove);
        cartRepository.save(cart);

        return convertToDto(cart);
    }

    public CartDTO updateCartItemQuantity(Long productId, int quantity) {
        Cart cart = getCurrentuserCart();
        CartItem itemToUpdate = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found in cart"));

        itemToUpdate.setQuantity(quantity);
        cartRepository.save(cart);

        return convertToDto(cart);
    }

    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public Cart getCurrentuserCart() {
        User user = userDetailsService.getCurrentUser();
        Cart cart=new Cart();
        if (user!=null) {

            // Get the user's cart, create one if it doesn't exist
            cart = cartRepository.findByUserId(user.getId())
                    .orElse(new Cart());
            cart.setUser(user);
        }
        return cart;
    }
    public CartDTO getCurrentuserCartDTO() {
        User user = userDetailsService.getCurrentUser();
        Cart cart=new Cart();
        if (user!=null) {

            // Get the user's cart, create one if it doesn't exist
            cart = cartRepository.findByUserId(user.getId())
                    .orElse(new Cart());
        }
        return convertToDto(cart);
    }

    private CartDTO convertToDto(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setTotalCartPrice(cart.getTotalCartPrice());
        dto.setItems(cart.getItems().stream()
                .map(this::convertItemToDto)
                .collect(Collectors.toList()));
        return dto;
    }
    private CartItemDTO convertItemToDto(CartItem item) {
        CartItemDTO dto = new CartItemDTO();
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPricePerItem(item.getProduct().getPrice());
        dto.setTotalPrice(item.getProduct().getPrice() * item.getQuantity());
        return dto;
    }
    public CartDTO toCartDTO(Cart cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());

        List<CartItemDTO> itemDTOs = cart.getItems().stream()
                .map(item -> {
                    CartItemDTO itemDTO = new CartItemDTO();
                    itemDTO.setId(item.getId());
                    itemDTO.setProductId(item.getProduct().getId());
                    itemDTO.setProductName(item.getProduct().getName());
                    itemDTO.setQuantity(item.getQuantity());
                    return itemDTO;
                }).collect(Collectors.toList());

        cartDTO.setItems(itemDTOs);
        return cartDTO;
    }


    public Cart getCurrentuserCartForCheckout()  {

        User user = userDetailsService.getCurrentUser();
        Cart cart=new Cart();
        if (user!=null) {

            // Get the user's cart, create one if it doesn't exist
            cart = cartRepository.findByUserId(user.getId())
                    .orElse(null);
        }

        return cart;
    }
}

