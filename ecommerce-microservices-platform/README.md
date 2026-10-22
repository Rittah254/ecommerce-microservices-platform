# E-Commerce Order & Inventory Management Platform

A Java 21, Spring Boot microservices project implementing Product, Inventory, Order and API Gateway services with MySQL, Kafka, REST, validation, DTOs, exception handling, pagination, sorting, Docker and tests.

## Services

| Service | Port | Database |
|---|---:|---|
| API Gateway | 8080 | none |
| Product Service | 8081 | product_db |
| Inventory Service | 8082 | inventory_db |
| Order Service | 8083 | order_db |
| Kafka | 9092 | - |
| MySQL | 3306 | product_db, inventory_db, order_db |

## Business flow

1. Create product through the gateway.
2. Create inventory for the product.
3. Check inventory.
4. Create an order through the gateway.
5. Order Service validates products through Product Service REST.
6. Order Service checks inventory through Inventory Service REST.
7. Order Service saves the order and publishes `ORDER_CREATED` to Kafka.
8. Inventory Service consumes the event and reserves stock.
9. Check inventory again.
10. Update order status.

## Local prerequisites

- Java 21
- Maven 3.9+
- MySQL 8+
- Docker Desktop (recommended)
- Postman

## Run infrastructure

From the `docker` directory:

```bash
docker compose up -d mysql kafka
```

Then start the four Spring Boot applications from their service directories.

Or build and start everything:

```bash
docker compose up --build
```

## Local application URLs

- Gateway: http://localhost:8080
- Product: http://localhost:8081
- Inventory: http://localhost:8082
- Order: http://localhost:8083
- Swagger Product: http://localhost:8081/swagger-ui.html
- Swagger Inventory: http://localhost:8082/swagger-ui.html
- Swagger Order: http://localhost:8083/swagger-ui.html
- Health endpoints: `/actuator/health`

## Important

For the Docker version, service-to-service URLs use Docker service names such as `http://product-service:8081`, not `localhost`.

## Main acceptance flow

```text
Client
  |
  v
API Gateway :8080
  |
  +--> Product Service :8081 --> product_db
  |
  +--> Inventory Service :8082 --> inventory_db
  |
  +--> Order Service :8083 --> order_db
                    |
                    +--> REST --> Product Service
                    |
                    +--> REST --> Inventory Service
                    |
                    +--> Kafka --> order-events --> Inventory Service
```
