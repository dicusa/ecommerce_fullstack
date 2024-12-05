import React, { useEffect, useContext } from "react";
import axios from "axios";
import "../css/Orders.css";
import { GlobalContext } from "../context/GlobalContext";
import api from "../api/interceptor/axiosInterceptor";
import { Link } from "react-router-dom";

const Orders = () => {
  const { orders, setOrders } = useContext(GlobalContext);

  useEffect(() => {
    // Fetch orders from the backend
    api
      .get("/orders")
      .then((response) => {
        console.info("Orders: ", response);
        setOrders(response.data);
      })
      .catch((error) => console.error("Error fetching orders:", error));
  }, [setOrders]);
  const handleStatusChange = (orderId, nextStatus) => {
    api
      .post(`/orders/${orderId}`, null, {
        params: { status: nextStatus },
      })
      .then((response) => {
        console.log("Order status updated:", response.data);
        // Update the local order state
        setOrders((prevOrders) =>
          prevOrders.map((order) =>
            order.id === orderId ? { ...order, status: nextStatus } : order
          )
        );
      })
      .catch((error) => console.error("Error updating order status:", error));
  };

  const getNextStatus = (currentStatus) => {
    switch (currentStatus) {
      case "CREATED":
        return null; // No next status for CREATED
      case "PAID":
        return "SHIPPED"; // Transition from PAID to SHIPPED
      case "SHIPPED":
        return "COMPLETED"; // Transition from SHIPPED to COMPLETED
      default:
        return null; // COMPLETED and CANCELED have no transitions
    }
  };

  return (
    <div className="orders-container">
      <Link to="/">Back To Dashboard</Link>

      <h1>Order Management</h1>
      <table className="orders-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Products</th>
            <th>Total Price</th>
            <th>Status</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => {
            const nextStatus = getNextStatus(order.status);
            return (
              <tr key={order.id}>
                <td>{order.id}</td>
                <td>{order.items.join(", ")}</td>
                <td>${order.totalPrice}</td>
                <td>{order.status}</td>
                <td>
                  {nextStatus && (
                    <button
                      onClick={() => handleStatusChange(order.id, nextStatus)}
                    >
                      Move to {nextStatus}
                    </button>
                  )}
                  {order.status !== "COMPLETED" &&
                    order.status !== "CANCELED" && (
                      <button
                        onClick={() => handleStatusChange(order.id, "CANCELED")}
                      >
                        Cancel Order
                      </button>
                    )}
                </td>
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default Orders;
