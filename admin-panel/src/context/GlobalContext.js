import React, { createContext, useEffect, useState } from "react";

export const GlobalContext = createContext();

export const GlobalProvider = ({ children }) => {
  console.log("GlobalProvider rendered");
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [token, setToken] = useState(null);
  const [products, setProducts] = useState([]);
  const [orders, setOrders] = useState([]);
  const [users, setUsers] = useState([]);
  useEffect(() => {
    const tempToken = localStorage.getItem("jwtToken");
    if (tempToken != null) {
      setToken(tempToken);
    }
  }, []);
  return (
    <GlobalContext.Provider
      value={{
        isLoggedIn,
        setIsLoggedIn,
        token,
        setToken,
        products,
        setProducts,
        orders,
        setOrders,
        users,
        setUsers,
      }}
    >
      {children}
    </GlobalContext.Provider>
  );
};
