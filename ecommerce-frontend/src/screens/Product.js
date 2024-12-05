import React, { useEffect, useContext, useState } from "react";
import AuthContext from "../context/AuthContext";
import "../styles/ProductPage.css";
import ProductCard from "../components/ProductCard";
import { Link } from "react-router-dom";
const Products = () => {
  const { getToken } = useContext(AuthContext);
  const [products, setProducts] = useState([]);
  const [page, setPage] = useState(0);
  const [size, setSize] = useState(9); // 3 rows of 3 products
  const [totalPages, setTotalPages] = useState(0);

  useEffect(() => {
    fetchProducts();
  }, [page]);

  const fetchProducts = async () => {
    const response = await fetch(
      `http://localhost:8080/api/products?page=${page}&size=${size}`
    );
    // {
    //   headers: {
    //     Authorization: `Bearer ${getToken()}`,
    //   },
    // }
    if (response.ok) {
      const data = await response.json();
      setProducts(data.content);
      setTotalPages(data.totalPages);
    } else {
      alert("Failed to fetch products");
    }
  };

  const handleNextPage = () => {
    if (page < totalPages - 1) {
      setPage(page + 1);
    }
  };

  const handlePreviousPage = () => {
    if (page > 0) {
      setPage(page - 1);
    }
  };

  return (
    <div>
      <div className="product-grid">
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
      <div className="pagination">
        <button onClick={handlePreviousPage} disabled={page === 0}>
          Previous
        </button>
        <button onClick={handleNextPage} disabled={page >= totalPages - 1}>
          Next
        </button>
      </div>
      <Link to="/cart">Check Cart</Link>
    </div>
  );
};

export default Products;
