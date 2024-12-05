import axios from "axios";
import { useState, useEffect, useContext } from "react";
import AuthContext from "../context/AuthContext";
import { CartContext } from "../context/CartContext";
import { Link } from "react-router-dom";

const Cart = () => {
  const { cart, setCart, removeFromCart } = useContext(CartContext);
  const { getToken } = useContext(AuthContext);
  useEffect(() => {
    const fetchCart = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/cart/myCart`,
          {
            headers: {
              Authorization: `Bearer ${getToken()}`,
            },
          }
        );
        console.info("===cart:", response.data);
        if (response.status == 200) {
          setCart(response.data);
        }
      } catch (error) {
        console.error("Error fetching cart:", error);
      }
    };

    fetchCart();
  }, [setCart]);

  const removeItem = async (productId) => {
    try {
      const response = await axios.delete(
        `http://localhost:8080/api/cart/items/${productId}`,
        {
          headers: {
            Authorization: `Bearer ${getToken()}`,
          },
        }
      );
      console.info("===cart:", response.data);
      if (response.status == 200) {
        setCart(response.data);
      }
    } catch (error) {
      console.error("Failed to remove item:", error);
    }
  };

  const updateQuantity = async (productId, quantity) => {
    try {
      if (quantity < 1) return removeItem(productId);
      const response = await axios.post(
        `http://localhost:8080/api/cart/items/${productId}`,
        null,
        {
          params: {
            quantity: quantity,
          },
          headers: {
            Authorization: `Bearer ${getToken()}`,
          },
        }
      );
      console.info("===cart:", response.data);
      if (response.status == 200) {
        setCart(response.data);
      }
    } catch (error) {
      console.error("'Failed to update quantity:", error);
    }
  };

  if (!cart) return <p>Loading cart...</p>;

  return (
    <div>
      <h2>Your Cart</h2>
      {cart.items.length === 0 ? (
        <div><p>Your cart is empty.</p>
        <Link to="/products">Browse Product</Link>
        </div>
      ) : (
        <div>
          <ul>
            {cart.items.map((item) => (
              <li key={item.productId}>
                <div className="cart-item">
                  <div className="item-details">
                    <h4>{item.productName}</h4>
                    <div className="quantity-controls">
                      <button
                        onClick={() => {
                          updateQuantity(item.productId, item.quantity - 1);
                        }}
                      >
                        -
                      </button>
                      <span>{item.quantity}</span>
                      <button
                        onClick={() => {
                          updateQuantity(item.productId, item.quantity + 1);
                        }}
                      >
                        +
                      </button>
                    </div>

                    <div classname="prices">
                      <span>{item.pricePerItem}</span>
                      <span>Total = {item.totalPrice}</span>
                    </div>
                  </div>
                  <button onClick={() => removeItem(item.productId)}>
                    Remove
                  </button>
                </div>
              </li>
            ))}
          </ul>
          <span>Total = {cart.totalCartPrice}</span>
          <Link to="/checkout">Checkout</Link>
        </div>
      )}
    </div>
  );
};

export default Cart;
