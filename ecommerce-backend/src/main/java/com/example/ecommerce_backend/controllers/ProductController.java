package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.kafka.producer.KafkaProducer;
import com.example.ecommerce_backend.models.Product;
import com.example.ecommerce_backend.models.ProductDTO;
import com.example.ecommerce_backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        Page<ProductDTO> products = productService.getAllProducts(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = productService.saveProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestBody String productDetails) {
        kafkaProducer.sendMessage(productDetails);
        return new ResponseEntity<>("Product update event sent to Kafka!",HttpStatus.OK);
//        return productService.getProductById(id)
//                .map(existingProduct -> {
//                    product.setId(existingProduct.getId());
//                    Product updatedProduct = productService.saveProduct(product);
//                    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
//                })
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(existingProduct -> {
                    productService.deleteProduct(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}