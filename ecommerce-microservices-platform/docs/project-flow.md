# Project Flow

## Synchronous path

Order Service uses REST to obtain current product data and inventory availability.

## Asynchronous path

After the order is saved, Order Service publishes `ORDER_CREATED` to Kafka topic `order-events`.

Inventory Service consumes the event and reserves the requested stock.

## Why both?

REST is useful when the order needs an immediate answer. Kafka is useful for event-driven work that can happen after the order has been accepted and for reducing direct coupling between the services.
