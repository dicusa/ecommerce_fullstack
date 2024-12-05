import axios from "axios";
import { useContext } from "react";

// Create an Axios instance
const api = axios.create({
  baseURL: "http://localhost:8080/api",
});

// Add a request interceptor to include the JWT token
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("jwtToken");
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

api.interceptors.response.use(
  (res) => res,
  (err) => {
    const {
      config,
      response: { status },
    } = err;

    if (status === 401) {
      window.location.pathname = "/login";
    } else {
      return Promise.reject(err);
    }
  }
);

export default api;
