import React, { useContext } from "react";
import AuthContext from "../context/AuthContext";
import { Link, useNavigate } from "react-router-dom";

const Dashboard = () => {
  const { logout } = useContext(AuthContext);
  const navigate = useNavigate();
  const handleLogout = () => {
    logout();
    navigate("/"); // Redirect to the login page after logging out
  };
  return (
    <div>
      <h1>Dashboard</h1>
      <p>
        Welcome to your dashboard! Here you can manage your account, view
        orders, and more.
      </p>
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/products">Products</Link>
          </li>
        </ul>
      </nav>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default Dashboard;
