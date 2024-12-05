import React, { useEffect, useContext, useState } from "react";
import AuthContext from "../context/AuthContext";
import "../styles/ProductPage.css";
import axios from "axios";
import { CartContext } from "../context/CartContext";
import axiosInstance from "../api/interceptor";

const ProductCard = ({ product }) => {
  const { getToken } = useContext(AuthContext);
  const { addToCart } = useContext(CartContext);

  const handleAddToCart = async () => {
    const cartId = 1; // Assuming a single cart for now
    const quantity = 1;

    await axiosInstance
      .post(`/api/cart/${cartId}/add`, null, {
        params: {
          productId: product.id,
          quantity: quantity,
        },
      })
      .then((response) => {
        if (response.status == 200) {
          addToCart({ product, quantity });
          alert(`${product.name} added to cart!`);
        }
      })
      .catch((err) => {
        alert(err);
      });

    
  };

  return (
    <div className="product-card">
      <img src={product.imageUrl} alt={product.name} />
      <h3>{product.name}</h3>
      <p>{product.description}</p>
      <p>${product.price}</p>
      <button onClick={handleAddToCart}>Add to Cart</button>
    </div>
  );
};

export default ProductCard;
