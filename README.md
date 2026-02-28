# RevShop P2 - Full Stack E-Commerce Platform

A modern e-commerce web application built with Spring Boot backend and Angular frontend, featuring user authentication, product management, and order processing.

## 🛍️ Features

- **User Authentication & Authorization**
  - JWT-based authentication
  - Role-based access control (Admin/User)
  - Secure password handling

- **Product Management**
  - Browse products by category
  - Search and filter functionality
  - Product details and reviews
  - Admin product CRUD operations

- **Shopping Cart & Orders**
  - Add/remove items from cart
  - Checkout process
  - Order history tracking
  - Order management for admins

- **Admin Dashboard**
  - Product inventory management
  - Order management
  - User management
  - Sales analytics

## 🏗️ Tech Stack

### Backend
- **Java 17** with Spring Boot 3.2.5
- **Spring Security** with JWT authentication
- **Spring Data JPA** for data persistence
- **MySQL** database (with H2 for testing)
- **Swagger/OpenAPI** for API documentation
- **Maven** for dependency management

### Frontend
- **Angular 19** with TypeScript
- **Angular Material** for UI components
- **RxJS** for reactive programming
- **HTML5/CSS3** with responsive design

### Testing
- **JUnit 4** and **Mockito** for backend testing
- **Karma** and **Jasmine** for frontend testing

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Node.js 18+ and npm
- MySQL 8.0+
- Maven 3.6+

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/revshop_p2.git
   cd revshop_p2
   
2. Database Configuration
bash
# Create MySQL database
mysql -u root -p
CREATE DATABASE revshop_p2_db;
 
# Import database schema
mysql -u root -p revshop_p2_db < revshop_p2_db.sql

3. Configure Application Properties Update src/main/resources/application.properties with your database credentials:
properties
spring.datasource.url=jdbc:mysql://localhost:3306/revshop_p2_db
spring.datasource.username=your_username
spring.datasource.password=your_password

4.Run the Backend
bash
# Using Maven wrapper
./mvnw spring-boot:run
 
# Or using Maven
mvn spring-boot:run
The backend will be available at http://localhost:8080

Frontend Setup
Navigate to frontend directory
bash
cd revshop-ui
Install dependencies
bash
npm install
Run the development server
bash
ng serve
The frontend will be available at http://localhost:4200

📚 API Documentation
Once the backend is running, you can access the interactive API documentation at:

Swagger UI: http://localhost:8080/swagger-ui.html
OpenAPI JSON: http://localhost:8080/v3/api-docs

🧪 Testing
Backend Tests
bash
# Run all tests
./mvnw test
 
# Run specific test class
./mvnw test -Dtest=UserServiceTest
 
# Run with coverage
./mvnw jacoco:report
Frontend Tests
bash
# Run unit tests
ng test
 
# Run end-to-end tests
ng e2e
 
# Run tests with coverage
ng test --code-coverage


📁 Project Structure
revshop_p2/
├── src/main/java/com/revshop/          # Backend source code
│   ├── controller/                      # REST controllers
│   ├── service/                         # Business logic
│   ├── repository/                      # Data access layer
│   ├── model/                          # Entity classes
│   ├── config/                         # Configuration classes
│   └── security/                       # Security configuration
├── src/main/resources/                  # Backend resources
│   ├── application.properties          # Application configuration
│   └── static/                         # Static resources
├── revshop-ui/                          # Frontend Angular application
│   ├── src/app/                        # Angular source code
│   │   ├── components/                 # Angular components
│   │   ├── services/                   # Angular services
│   │   ├── models/                     # TypeScript models
│   │   └── modules/                    # Angular modules
│   ├── src/assets/                     # Static assets
│   └── angular.json                    # Angular configuration
├── pom.xml                             # Maven configuration
├── revshop_p2_db.sql                   # Database schema
└── README.md                           # This file
🔧 Configuration
Environment Variables
Create a .env file in the root directory:

env
# Database Configuration
DB_HOST=localhost
DB_PORT=3306
DB_NAME=revshop_p2_db
DB_USERNAME=your_username
DB_PASSWORD=your_password
 
# JWT Configuration
JWT_SECRET=your_jwt_secret_key
JWT_EXPIRATION=86400000
 
# Server Configuration
SERVER_PORT=8080
Database Schema
The database schema is available in revshop_p2_db.sql. Import this file to set up the initial database structure.

🚀 Deployment
Backend Deployment
bash
# Build the JAR file
./mvnw clean package
 
# Run the JAR file
java -jar target/revshop_p2-0.0.1-SNAPSHOT.jar
Frontend Deployment
bash
# Build for production
ng build --configuration production
 
# The build artifacts will be in dist/revsshop-ui/
🤝 Contributing
Fork the repository
Create a feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add some amazing feature')
Push to the branch (git push origin feature/amazing-feature)
Open a Pull Request
📝 License
This project is licensed under the MIT License - see the LICENSE file for details.

📞 Support
For support and questions:

Create an issue in this repository
Email: your-email@example.com
🙏 Acknowledgments
Spring Boot for the backend framework
Angular for the frontend framework
MySQL for the database
All contributors who have helped shape this project
   
