import React, { useState } from "react";
import "../../css/ProductEditModal.css";
import api from "../../api/interceptor/axiosInterceptor";

const ProductEditModal = ({ product, closeModal }) => {
  const [formData, setFormData] = useState({
    name: product.name,
    price: product.price,
    description: product.description || "",
    imageUrl: product.imageUrl || "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };
  //   {
  //     "event": "update",
  //     "attributes": ["name", "price", "description", "imageUrl"],
  //     "productID": 123,
  //     "name": "Updated Product Name",
  //     "price": 99.99,
  //     "description": "Updated description",
  //     "imageUrl": "https://example.com/image.jpg",
  //     "messageID": "f3e1b2c8-d764-4a7b-a638-5d08f7bfb8d3"
  //   }
  const handleUpdate = () => {
    const updatedProduct = {
      event: "update",
      attributes: Object.keys(formData),
      productID: product.id,
      ...formData,
      messageID: crypto.randomUUID(),
    };
    console.info("updatedProduct: ", updatedProduct);
    api
      .put("/products/update", updatedProduct)
      .then((response) => {
        console.log("Product updated successfully:", response.data);
        closeModal();
      })
      .catch((error) => {
        console.error("Error updating product:", error);
      });
  };

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h2>Edit Product</h2>
        <form>
          <label>
            Name:
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
            />
          </label>
          <label>
            Price:
            <input
              type="number"
              name="price"
              value={formData.price}
              onChange={handleChange}
            />
          </label>
          <label>
            Description:
            <textarea
              name="description"
              value={formData.description}
              onChange={handleChange}
            />
          </label>
          <label>
            Image URL:
            <input
              type="text"
              name="imageUrl"
              value={formData.imageUrl}
              onChange={handleChange}
            />
          </label>
        </form>
        <button onClick={handleUpdate}>Update</button>
        <button onClick={closeModal}>Cancel</button>
      </div>
    </div>
  );
};

export default ProductEditModal;
