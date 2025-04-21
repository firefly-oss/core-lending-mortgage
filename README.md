# Core Lending Mortgages Microservice

## Overview
The Core Lending Mortgages Microservice is a key component of the Firefly platform, designed to manage mortgage applications and related processes. This microservice provides a comprehensive API for handling mortgage applications, including status tracking, payment schedules, property information, insurance details, and document management.

## Architecture

### Module Structure
The project is organized into the following modules:

- **core-lending-mortgages-core**: Contains the business logic and service implementations
- **core-lending-mortgages-interfaces**: Contains DTOs, interfaces, and enums used across the application
- **core-lending-mortgages-models**: Contains database entity models and repositories
- **core-lending-mortgages-web**: Contains REST controllers and web-related configurations

### Technology Stack
- **Java 21**: Core programming language
- **Spring Boot**: Application framework
- **Spring WebFlux**: Reactive web framework
- **Project Reactor**: Reactive programming library
- **Maven**: Build and dependency management
- **Docker**: Containerization
- **Swagger/OpenAPI**: API documentation
- **Lombok**: Boilerplate code reduction
- **Jackson**: JSON serialization/deserialization

## API Documentation

The microservice provides a RESTful API with the following main resources:

### Mortgage Applications
- Manage mortgage applications and their lifecycle
- Track status changes with detailed history
- Support for filtering and pagination

### Mortgage Payments
- Manage payment schedules
- Track payment records
- Calculate payment details

### Property Management
- Store and retrieve property information
- Link properties to mortgage applications

### Document Management
- Upload and manage mortgage-related documents
- Track document status and metadata

### Insurance
- Manage mortgage insurance information
- Link insurance to mortgage applications

### Contracts
- Manage mortgage contracts
- Track contract status and details

### Disbursements
- Manage mortgage disbursements
- Track disbursement status and details

## Setup and Installation

### Prerequisites
- Java 21
- Maven 3.8+
- Docker (for containerization)

### Building the Application
```bash
mvn clean install
```

### Running Locally
```bash
java -jar core-lending-mortgages-web/target/core-lending-mortgages.jar
```

### Running with Docker
```bash
# Build the Docker image
docker build -t core-lending-mortgages .

# Run the Docker container
docker run -p 8080:8080 core-lending-mortgages
```

## Configuration

The application can be configured using standard Spring Boot configuration mechanisms:

- **application.properties/application.yml**: Main configuration file
- **Environment Variables**: Override configuration properties
- **Command Line Arguments**: Override configuration properties

Key configuration properties:

- Database connection settings
- Security settings
- Logging configuration
- Integration endpoints

## Deployment

### CI/CD Pipeline
The project uses GitHub Actions for CI/CD:

1. On push to main or develop branch, the pipeline is triggered
2. Maven build is executed with tests
3. Docker image is built
4. Docker image is published to Azure Container Registry

### Deployment Environments
- **Development**: Automatically deployed from the develop branch
- **Production**: Manually promoted from the develop branch

## Contributing

### Development Workflow
1. Create a feature branch from develop
2. Implement changes
3. Write tests
4. Create a pull request to develop
5. After review and approval, merge to develop

### Coding Standards
- Follow Java coding conventions
- Write unit tests for all new features
- Document public APIs
- Keep methods small and focused
