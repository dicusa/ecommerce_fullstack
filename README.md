# E-commerce Platform

## Project Description
This project is a fully functional e-commerce platform designed using **ReactJS** on the frontend, **Spring Boot** on the backend, and a **SQL database** for data persistence. The platform showcases a typical online shopping experience with features like product browsing, adding items to the cart, updating quantities, and placing orders.

## Technologies Used
- **Frontend:** ReactJS
- **Backend:** Spring Boot
- **Database:** MySQL
- **Authentication:** JWT-based authentication
- **Containerization:** Docker
- **Version Control:** GitHub
- **Others:** Axios for API calls

## Features Implemented
- User Authentication using JWT tokens.
- Product listing with pagination.
- Add-to-cart functionality.
- Cart management (updating item quantities, removing items).
- Order placement.
- Backend APIs for managing products, orders, and users.

## Future Suggestions
- Add payment gateway integration.
- Implement product search and filtering.
- Add user reviews and ratings for products.
- Implement multi-language support.
- Add an admin panel for product, order and user management.

## Project Diagrams

### 1. UML Diagram
![image](https://github.com/user-attachments/assets/14e141a2-ca9a-4daf-8e90-582a6163494a)

### 2. Entity Relationship Diagram (ERD)
![image](https://github.com/user-attachments/assets/e4905213-33f2-481c-9738-1ed636786bab)

### 3. Class Diagram
![image](https://github.com/user-attachments/assets/c83b2357-ac87-4fe7-8b06-fc34a067fa5d)

How to Run Locally
Clone the repository:

## How to Run Locally

Follow these steps to set up and run the project locally using the pre-built Docker images included in the repository.

### Prerequisites

Ensure you have the following installed on your local machine:

1. **Docker**: [Install Docker](https://docs.docker.com/get-docker/)
2. **Docker Compose**: [Install Docker Compose](https://docs.docker.com/compose/install/)

### Steps

1. **Clone the repository**:
   Clone the repository to your local machine:
   > git clone https://github.com/dicusa/ecommerce_fullstack.git
   > cd ecommerce_fullstack

2. **Use the pre-built Docker images**:
   Since the Docker images are already provided in the repository(packages), you do not need to build them.
   Use the following command to start the containers:
   >docker-compose up
   
3. **Access the services**:
   Once the containers are up and running, you can access the application:

   Frontend: Open a browser and navigate to http://localhost:3000 for the React frontend.
   Backend: The backend API (Spring Boot) will be running at http://localhost:8080.
   Database: The MySQL database will be accessible at localhost:3306.
   Verify the database: You can connect to the MySQL database using a MySQL client like DBeaver or MySQL Workbench with the following credentials:

    Host: localhost
    Port: 3306
    Username: root
    Password: your_password
   
4. **Stopping the Services**
   To stop the running containers, run the following command:
   > docker-compose down


