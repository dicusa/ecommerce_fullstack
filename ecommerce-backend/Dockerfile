# Use the official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the build stage to the current stage
COPY target/ecommerce-backend-0.0.1-SNAPSHOT.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker

# Expose the application port (8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
