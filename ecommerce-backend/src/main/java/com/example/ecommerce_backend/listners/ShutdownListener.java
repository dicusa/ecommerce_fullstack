package com.example.ecommerce_backend.listners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;


@Component

public class ShutdownListener implements ApplicationListener<ContextClosedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ShutdownListener.class);

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        logger.info("Application context is closing. Reason: {}", event.getApplicationContext().toString());
        logger.info("Source of shutdown: {}", event.getSource());
        logger.info("Timestamp: {}", event.getTimestamp());
    }
}