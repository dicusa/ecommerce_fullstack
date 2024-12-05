package com.example.ecommerce_backend.kafka.consumer;

import com.example.ecommerce_backend.services.ProductService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ProductKafkaConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private ProductService productService;

    // Kafka listener method to listen to product updates
    @KafkaListener(topics = "product-updates", groupId = "e-commerce-consumer-group")
    public void consumeProductUpdate(String productUpdateMessage) {
        System.out.println("Received product update: " + productUpdateMessage);
        try {
            // Parse the incoming message
            JsonNode jsonNode = objectMapper.readTree(productUpdateMessage);
            String eventType = jsonNode.get("event").asText();

            // Delegate to ProductService based on event type
            if ("create".equalsIgnoreCase(eventType)) {
//                productService.createProduct(jsonNode);
            } else if ("update".equalsIgnoreCase(eventType)) {
                productService.updateProduct(jsonNode);
            } else {
                System.out.println("Unknown event type: " + eventType);
            }
        } catch (Exception e) {
            System.err.println("Error processing Kafka message: " + e.getMessage());
            e.printStackTrace();
        }
        // Process the message (e.g., update the product in the database)
        // For example, parse the product update message and save to the database
        // This can be done by calling a service or repository to persist the data
    }
}

