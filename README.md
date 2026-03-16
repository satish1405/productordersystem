A Spring Boot REST API for managing products, carts, and orders with role-based access (Admin & User).
This project demonstrates backend development concepts including:
Product management
Cart system
Order placement
Inventory validation
Transaction handling
REST APIs


Technologies Used
Java 17
Spring Boot
Spring Data JPA
Hibernate ORM
MySQL
Postman
Maven

Setup Instructions
1. Clone the repository
   git clone https://github.com/yourusername/product-order-system.git
2. Open Project
   Import the project into:
    1.IntelliJ
    2. Eclipse
    3. Maven
3. Configure Database
   run : CREATE DATABASE product_order_db;
Update application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_order_db
spring.datasource.username=root
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4. Run Application
   ProductordersystemApplication.java
Server will start at:
http://localhost:8080


DATABASE SCHEMA
Users
Column	Type
id	BIGINT
name	VARCHAR
role	VARCHAR

Users
Column	Type
id	BIGINT
name	VARCHAR
role	VARCHAR
Eclipse

VS Code
