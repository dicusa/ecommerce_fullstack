import axios from 'axios';
import { useHistory } from 'react-router-dom';

// Create Axios instance
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080', 
    headers: {
        'Content-Type': 'application/json'
    }
});

// Request Interceptor
axiosInstance.interceptors.request.use(
    (config) => {
        const authToken = localStorage.getItem('authToken'); // Retrieve token from localStorage
        if (authToken) {
            config.headers.Authorization = `Bearer ${authToken}`; // Append token to request
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Response Interceptor
axiosInstance.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        if (error.response && error.response.status === 401) { // 401 is unauthorized error
            localStorage.removeItem('authToken'); // Clear token from localStorage
            window.location.href = '/login'; // Redirect to login page
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;
