import React, { createContext, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  // const [isAuthenticated, setIsAuthenticated] = useState(false);
  const login = (token) => {
    localStorage.setItem("authToken", token);
  };

  const logout = () => {
    localStorage.removeItem("authToken");
  };

  const getToken = () => {
    return localStorage.getItem("authToken");
  };

  const isAuthenticated = () => {
    console.info("===", localStorage.getItem("authToken"));
    return localStorage.getItem("authToken") != null;
  };
  return (
    <AuthContext.Provider value={{ login, logout, getToken, isAuthenticated }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthContext;
