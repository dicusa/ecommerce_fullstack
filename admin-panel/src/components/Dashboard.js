import React, { useContext, useEffect } from "react";
import { Link } from "react-router-dom";
import "../css/Dashboard.css";
import { GlobalContext } from "../context/GlobalContext";

const Dashboard = () => {
  return (
    <div className="dashboard-container">
      <h1>Admin Dashboard</h1>
      <div className="grid">
        <Link to="/products" className="grid-item">
          Products
        </Link>
        <Link to="/orders" className="grid-item">
          Orders
        </Link>
        <Link to="/users" className="grid-item">
          Users
        </Link>
      </div>
    </div>
  );
};

export default Dashboard;
