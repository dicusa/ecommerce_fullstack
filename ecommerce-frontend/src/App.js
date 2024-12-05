import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import Login from "./screens/Login";
import Products from "./screens/Product";
import Dashboard from "./screens/Dashboard";
import Homepage from "./screens/Homepage";
import ProtectedRoute from "./components/ProtectedRoute";
import Cart from "./screens/Cart";
import { CartProvider } from "./context/CartContext";
import Checkout from "./screens/Checkout ";

function App() {
  return (
    <AuthProvider>
      <CartProvider>
        <Router>
          <Routes>
            <Route path="/" element={<Homepage />} />
            <Route path="/login" element={<Login />} />
            <Route path="/products" element={<Products />} />
            <Route
              path="/dashboard"
              element={
                <ProtectedRoute>
                  <Dashboard />
                </ProtectedRoute>
              }
            />
            <Route
              path="/cart"
              element={
                <ProtectedRoute>
                  <Cart />
                </ProtectedRoute>
              }
            />
            <Route
              path="/checkout"
              element={
                <ProtectedRoute>
                  <Checkout />
                </ProtectedRoute>
              }
            />
          </Routes>
        </Router>
      </CartProvider>
    </AuthProvider>
  );
}

export default App;
