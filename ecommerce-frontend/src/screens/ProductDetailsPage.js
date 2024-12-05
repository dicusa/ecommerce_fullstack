import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const ProductDetailsPage = () => {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);
  const { getToken } = useContext(AuthContext);

  useEffect(() => {
    const fetchProducts = async () => {
      const response = await fetch(
        `http://localhost:8080/api/products/${productId}`,
        {
          headers: {
            Authorization: `Bearer ${getToken()}`,
          },
        }
      );

      if (response.ok) {
        const data = await response.json();
        setProduct(data);
      } else {
        console.error("Error fetching product details:");
      }
    };
    fetchProducts();
    // Fetch product details based on the productId
  }, [productId]);

  if (!product) return <p>Loading...</p>;

  return (
    <div>
      <h1>{product.name}</h1>
      <p>{product.description}</p>
      <p>Price: ${product.price}</p>
      {/* Add more details as needed */}
    </div>
  );
};

export default ProductDetailsPage;
