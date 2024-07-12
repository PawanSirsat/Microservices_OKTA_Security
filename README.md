
# MakeMyTrip Microservices Project

This project is a microservices architecture for the "MakeMyTrip" application, implemented using Spring Boot with Okta security. The architecture includes an API gateway, config server, hotel service, rating service, service registry, and user service.

## Components

### 1. API Gateway
- **Description:** Acts as a single entry point for all clients to access the various microservices.
- **Tags:** `Spring Boot`, `Zuul`, `Okta`, `API Gateway`

### 2. Config Server
- **Description:** Centralized configuration management for all microservices, storing configurations in a Git repository.
- **Tags:** `Spring Boot`, `Spring Cloud Config`, `Git`, `Config Server`

### 3. Hotel Service
- **Description:** Manages hotel information including details, availability, and booking.
- **Tags:** `Spring Boot`, `Okta`, `Hotel Service`

### 4. Rating Service
- **Description:** Manages and stores user ratings and reviews for hotels.
- **Tags:** `Spring Boot`, `Okta`, `Rating Service`

### 5. Service Registry
- **Description:** Registers and manages all microservices instances using Eureka.
- **Tags:** `Spring Boot`, `Eureka`, `Service Registry`

### 6. User Service
- **Description:** Manages user information, authentication, and authorization.
- **Tags:** `Spring Boot`, `Okta`, `User Service`

## Getting Started

### Prerequisites
- Java 11+
- Maven
- Git

### Installation

1. **Clone the repository:**
   ```sh
   git clone https://github.com/PawanSirsat/Microservices_OKTA_Security.git
   cd makemytrip-microservices
   ```

2. **Configuration:**
   - Set up Okta for authentication and authorization.
   - Update configuration files in the Config Server repository.

3. **Build and Run:**
   ```sh
   mvn clean install
   cd service-registry
   mvn spring-boot:run
   cd ../config-server
   mvn spring-boot:run
   cd ../api-gateway
   mvn spring-boot:run
   cd ../hotel-service
   mvn spring-boot:run
   cd ../rating-service
   mvn spring-boot:run
   cd ../user-service
   mvn spring-boot:run
   ```

### Usage

- **API Gateway:** Access all services through the API Gateway at `http://localhost:8080`.
- **Config Server:** Configurations stored in Git and served from `http://localhost:8888`.
- **Service Registry:** View registered services at `http://localhost:8761`.

### Endpoints

- **Hotel Service:**
  - `GET /hotels` - List all hotels
  - `POST /hotels` - Add a new hotel
- **Rating Service:**
  - `GET /ratings` - List all ratings
  - `POST /ratings` - Add a new rating
- **User Service:**
  - `POST /users/register` - Register a new user
  - `POST /users/login` - User login

### Security

- **Okta Integration:** Secure all services using Okta for authentication and authorization. Update Okta configuration in each service’s properties file.

### License

This project is licensed under the MIT License.

---
