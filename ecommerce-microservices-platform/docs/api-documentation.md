# Main APIs

## Product
POST `/api/products`
GET `/api/products`
GET `/api/products/{id}`
GET `/api/products/search?name=...`
PUT `/api/products/{id}`
DELETE `/api/products/{id}`

## Inventory
POST `/api/inventory`
GET `/api/inventory/{productId}`
PUT `/api/inventory/{productId}/stock`
GET `/api/inventory/{productId}/availability`

## Order
POST `/api/orders` with `Idempotency-Key` header
GET `/api/orders`
GET `/api/orders/{id}`
GET `/api/orders/customer/{customerId}`
PUT `/api/orders/{id}/status?status=CONFIRMED`
DELETE `/api/orders/{id}`
