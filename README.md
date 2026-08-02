# Kafka Microservices - Event Driven Order Processing System

A production-style **event-driven microservices application** built using **Spring Boot, Apache Kafka, PostgreSQL, and Docker**.

This project demonstrates how independent services communicate asynchronously through Kafka events instead of direct service-to-service communication.

The system processes customer orders, publishes events, and allows multiple services to react independently.

---

# Architecture

```
                         Client
                           |
                           |
                    POST /orders
                           |
                           v
                  +----------------+
                  | Order Service  |
                  +----------------+
                           |
                           |
              Saves Order + Publishes Event
                           |
                           v
                      Apache Kafka
                    (order-created)
                           |
              +------------+------------+
              |                         |
              v                         v

     +----------------+        +----------------+
     | Stock Service  |        | Email Service  |
     +----------------+        +----------------+

     Consumes Event            Consumes Event
     Updates Stock             Sends Notification
```

---

# Technologies Used

## Backend

* Java 17
* Spring Boot 3
* Spring Data JPA
* Spring Kafka
* Maven

## Messaging

* Apache Kafka
* Zookeeper

## Database

* PostgreSQL

## DevOps

* Docker
* Docker Compose
* Environment-based configuration

---

# Services

## Order Service

Responsible for:

* Creating orders through REST API
* Persisting orders in PostgreSQL
* Publishing `OrderCreatedEvent` events to Kafka

## Stock Service

Responsible for:

* Consuming order events
* Processing inventory updates

## Email Service

Responsible for:

* Consuming order events
* Triggering customer notifications

---

# Event Flow

```
1. Client creates an order

        |
        v

2. Order Service saves order in PostgreSQL

        |
        v

3. Order Service publishes OrderCreatedEvent

        |
        v

4. Kafka distributes the event

        |
        +----------------+
        |                |
        v                v

5. Stock Service    Email Service

   Updates stock    Sends email
```

---

# Project Structure

```
kafka-microservices

├── base-domains
│
├── order-service
│
├── stock-service
│
├── email-service
│
├── docker-compose.yml
│
├── .env
│
└── README.md
```

---

# Running the Application

## Prerequisites

Make sure you have installed:

* Java 17+
* Maven
* Docker
* Docker Compose

## Configure Environment Variables

Create a `.env` file in the project root:

```env
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
```

---

## Build Services

Run:

```bash
mvn clean package
```

Build Docker images:

```bash
docker build -t order-service:1.0 ./order-service

docker build -t stock-service:1.0 ./stock-service

docker build -t email-service:1.0 ./email-service
```

---

## Start Application

Run everything with:

```bash
docker compose up --build
```

All services will start automatically:

* PostgreSQL
* Kafka
* Zookeeper
* Order Service
* Stock Service
* Email Service

---

# API Documentation

## Create Order

### POST

```
http://localhost:8080/orders
```

Request:

```json
{
    "customerName": "Harsh",
    "productName": "MacBook",
    "quantity": 1
}
```

Example:

```bash
curl -X POST http://localhost:8080/orders \
-H "Content-Type: application/json" \
-d '{
  "customerName":"Harsh",
  "productName":"MacBook",
  "quantity":1
}'
```

Response:

```json
{
    "id":1785646313839,
    "customerName":"Harsh",
    "product":"MacBook",
    "quantity":1
}
```

---

# Current Features

✅ Spring Boot Microservices Architecture

✅ REST API for Order Creation

✅ PostgreSQL Persistence

✅ Kafka Event Publishing

✅ Multiple Kafka Consumers

✅ Asynchronous Communication

✅ Dockerized Services

✅ Docker Compose Deployment

✅ Environment Variable Configuration

✅ Independent Service Deployment

---

# Upcoming Features

The following improvements are planned to make this project closer to a production-grade distributed system.

## API & Backend Improvements

* Swagger / OpenAPI API documentation
* Request validation
* Global exception handling
* Improved API responses
* Better logging and monitoring

## Testing

* Unit testing with JUnit 5
* Mockito-based service testing
* Integration testing
* Kafka integration testing using Testcontainers

## Kafka Improvements

* Kafka retry mechanisms
* Dead Letter Queue (DLQ)
* Consumer error handling
* Idempotent event processing
* Event schema management

## Microservice Improvements

* Dedicated Inventory Service with database persistence
* Stock update events
* Notification service improvements
* Service-to-service communication patterns

## DevOps & Cloud

* GitHub Actions CI/CD pipeline
* Docker image optimization
* Application health checks
* Spring Boot Actuator
* Prometheus metrics
* Grafana dashboards

## Production Architecture

* API Gateway
* Service discovery
* Centralized configuration
* Distributed tracing
* Kubernetes deployment
* Cloud deployment

---

# Learning Goals

This project focuses on understanding:

* Event-driven architecture
* Microservice communication patterns
* Kafka producers and consumers
* Containerized application deployment
* Database-backed Spring Boot services
* Distributed system design principles

---

# Author

Harsh S. Koyande

Backend Developer
Java | Spring Boot | Kafka | Microservices
