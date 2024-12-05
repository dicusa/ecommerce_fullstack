import React, { useContext, useState } from "react";
import { CartContext } from "../context/CartContext";
import axios from "axios";
import AuthContext from "../context/AuthContext";
import axiosInstance from "../api/interceptor";
import { useNavigate } from "react-router-dom";

const Checkout = () => {
  const { cart } = useContext(CartContext);
  const [shippingAddress, setShippingAddress] = useState("");
  const [paymentMethod, setPaymentMethod] = useState("Credit Card");
  const { getToken } = useContext(AuthContext);
  const navigate = useNavigate();
  const handleCheckout = async () => {
    try {
      const response = await axiosInstance.post("/api/checkout", {
        paymentMethod,
        shippingAddress,
      });
      if (response.status == 200) {
        alert(`order Placed with Order Number : ${response.data.orderId}`);
        navigate("/dashboard");
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Checkout</h2>
      <div>
        <h3>Items</h3>
        {cart.items.map((item) => (
          <div key={item.productId}>
            <p>
              {item.productName} x {item.quantity}
            </p>
          </div>
        ))}
      </div>
      <div>
        <h3>Total: ${cart.totalPrice}</h3>
      </div>
      <div>
        <input
          type="text"
          placeholder="Shipping Address"
          value={shippingAddress}
          onChange={(e) => setShippingAddress(e.target.value)}
        />
      </div>
      <div>
        <select
          value={paymentMethod}
          onChange={(e) => setPaymentMethod(e.target.value)}
        >
          <option value="Credit Card">Credit Card</option>
          <option value="PayPal">PayPal</option>
        </select>
      </div>
      <button onClick={handleCheckout}>Place Order</button>
    </div>
  );
};

export default Checkout;
