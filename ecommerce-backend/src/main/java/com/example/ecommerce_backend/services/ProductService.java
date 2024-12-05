package com.example.ecommerce_backend.services;

import com.example.ecommerce_backend.models.Product;
import com.example.ecommerce_backend.models.ProductDTO;
import com.example.ecommerce_backend.repositories.ProductRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDTO> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(this::convertToDTO);

    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void updateProduct(JsonNode jsonNode) {
        try {
            Long productId = jsonNode.get("productID").asLong();

            // Find the existing product
            Optional<Product> products = getProductById(productId);
            Product product;
            if (products.isPresent()){
                product = products.get();
                // Update specified attributes
                JsonNode attributesNode = jsonNode.get("attributes");
                if (attributesNode != null) {
                    for (JsonNode attributeNode : attributesNode) {
                        String attributeName = attributeNode.asText();
                        if (jsonNode.has(attributeName)) {
                            String updatedValue = jsonNode.get(attributeName).asText();
                            updateAttribute(product, attributeName, updatedValue);
                        }
                    }
                }

    // Save the updated product
    productRepository.save(product);
    System.out.println("Product updated: " + product.getId());
}

        } catch (Exception e) {
            System.err.println("Error updating product: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Product parseProductFromJson(JsonNode jsonNode) throws Exception {
        // Convert JSON to Product object
        return new ObjectMapper().treeToValue(jsonNode, Product.class);
    }

    private void updateAttribute(Product product, String attributeName, String updatedValue) {
        switch (attributeName) {
            case "description":
                product.setDescription(updatedValue);
                break;
            case "name":
                product.setName(updatedValue);
                break;
            case "quantity":
                product.setQuantity(Integer.parseInt(updatedValue));
                break;
            case "imageUrl":
                product.setImageUrl(updatedValue);
                break;
            case "price":
                product.setPrice(Double.parseDouble(updatedValue));
                break;
            default:
                System.err.println("Unknown attribute: " + attributeName);
        }
    }


    private ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImageUrl());
    }
}