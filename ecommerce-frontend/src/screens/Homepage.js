import React from "react";
import { Link } from "react-router-dom";

const Homepage = () => {
  return (
    <div>
      <h1>Welcome to Our E-commerce Platform</h1>
      <nav>
        <ul>
          <li>
            <Link to="/login">Login</Link>
          </li>
          <li>
            <Link to="/products">Products</Link>
          </li>
          <li>
            <Link to="/dashboard">Dashboard</Link>
          </li>
          <li>
            <Link to="/cart">My Cart</Link>
          </li>
        </ul>
      </nav>
    </div>
  );
};

export default Homepage;
