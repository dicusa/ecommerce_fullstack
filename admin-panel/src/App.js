import React, { useContext, useState } from "react";
import "./App.css";
import Dashboard from "./components/Dashboard";
import Login from "./components/Login";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { GlobalContext, GlobalProvider } from "./context/GlobalContext";
import Products from "./components/Products";
import Orders from "./components/Orders";

const App = () => {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/login" element={<Login />} />
          <Route path="/products" element={<Products />} />
          <Route path="/orders" element={<Orders />} />
          {/* <Route path="/users" element={<Users />} /> */}
        </Routes>
      </div>
    </Router>
  );
};

export default App;
