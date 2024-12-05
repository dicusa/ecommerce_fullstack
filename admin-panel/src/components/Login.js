import React, { useContext, useEffect, useState } from "react";
import "../css/Login.css"; // Add your styles
import axios from "axios";
import { GlobalContext } from "../context/GlobalContext";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const { setIsLoggedIn, setToken } = useContext(GlobalContext);
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const tempToken = localStorage.getItem("jwtToken");
    if (tempToken != null) {
      navigate("/");
    }
    setIsLoggedIn(false);
    setToken(null);
  }, []);
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/authenticate", {
        username: email,
        password,
      });

      // Save JWT token to localStorage
      const token = response.data.token;
      localStorage.setItem("jwtToken", token);
      setToken(token);
      // Mark user as logged in
      setIsLoggedIn(true);
    } catch (err) {
      setError("Invalid email or password");
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>Admin Panel Login</h2>
        {error && <p className="error">{error}</p>}
        <input
          type="text"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;
