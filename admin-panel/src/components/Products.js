import React, { useEffect, useContext, useState } from "react";
import "../css/Products.css";
import { GlobalContext } from "../context/GlobalContext";
import api from "../api/interceptor/axiosInterceptor";
import ProductEditModal from "./modals/ProductEditModal";
import { Link } from "react-router-dom";
const Products = () => {
  const { products, setProducts } = useContext(GlobalContext);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  useEffect(() => {
    // Fetch products from the backend
    api
      .get("/products")
      .then((response) => {
        console.info("Response:", response);
        setProducts(response.data.content);
      })
      .catch((error) => console.error("Error fetching products:", error));
  }, [setProducts]);

  const openModal = (product) => {
    setSelectedProduct(product);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setSelectedProduct(null);
    setIsModalOpen(false);
  };

  return (
    <div className="products-container">
      <Link to="/">Back To Dashboard</Link>

      <h1>Product Management</h1>
      <div className="product-list">
        {products.map((product) => (
          <div className="product-card" key={product.id}>
            <img src={product.imageUrl} />
            <h3>{product.name}</h3>
            <p>Price: ${product.price}</p>
            <button onClick={() => openModal(product)}>Edit</button>
          </div>
        ))}
      </div>
      {isModalOpen && (
        <ProductEditModal product={selectedProduct} closeModal={closeModal} />
      )}
    </div>
  );
};

export default Products;
