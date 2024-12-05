import React, { createContext, useReducer, useState } from "react";

// Initial cart state
const initialState = {
  cart: null,
};

// Create context
export const CartContext = createContext(initialState);

// Cart context provider component
export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState(null);

  const addToCart = (payload) => {
    if (!cart) {
      console.error("Cart is not loaded yet.");
      return;
    }
    const product = payload.product;
    const quantity = payload.quantity;

    const updatedCartItems = [...cart.items];
    const existingItemIndex = updatedCartItems.findIndex(
      (item) => item.id === product.id
    );

    if (existingItemIndex >= 0) {
      // If the product already exists in the cart, update the quantity
      updatedCartItems[existingItemIndex].quantity += quantity;
    } else {
      // If the product does not exist in the cart, add it
      updatedCartItems.push(product);
    }

    // Update the cart locally
    const updatedCart = { ...cart, items: updatedCartItems };
    setCart(updatedCart);
  };

  const removeFromCart = (productId) => {
    const updatedCartItems = cart.items.filter(
      (item) => item.productId !== productId
    );
    const updatedCart = { ...cart, items: updatedCartItems };
    setCart(updatedCart);
  };

  return (
    <CartContext.Provider value={{ cart, setCart, addToCart, removeFromCart }}>
      {children}
    </CartContext.Provider>
  );
};
