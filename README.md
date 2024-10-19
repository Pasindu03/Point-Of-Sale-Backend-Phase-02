---

# Point of Sale Backend (Phase 02)

## Overview
The Point of Sale (POS) Backend is designed to manage customers, products, orders, and inventory for a retail system. It provides a set of RESTful APIs that allow operations like creating orders, adding products, and managing customer details.

This backend solution is built using Spring Boot, MySQL, and follows best practices for REST API development. It's optimized for scalability, performance, and security.

## Features
- **Customer Management**: Create, update, delete, and fetch customer details.
- **Product Management**: Manage inventory, including adding, updating, deleting, and fetching product information.
- **Order Processing**: Place orders, view order history, and manage order details.
- **Inventory Tracking**: Track and manage stock levels of products, including reorder levels and pricing.
- **CRUD Operations**: Full support for Create, Read, Update, and Delete operations across all entities.
- **DTOs & Entities**: Implementation of Data Transfer Objects (DTOs) for effective communication between the client and server, and entities for database management.

## Technologies Used
- **Backend Framework**: Spring Boot
- **Database**: MySQL
- **ORM**: Hibernate (JPA)
- **API Documentation**: POSTMAN Application - [POS API Docs](https://www.postman.com/speeding-escape-302843/workspace/point-of-sale-phase-02/collection/32456863-46661267-c44d-43c4-b127-cc9d80e7db2d?action=share&creator=32456863)
- **Build Tool**: Maven

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- MySQL 8.0+

---
## API Documentation

### 1. **Items API**

#### 1.1 Create a New Item
**Endpoint**: `POST /api/v1/items`

- **Description**: Creates a new item in the system.
- **Request Body**:
    ```json
    {
      "itemCode": "I00-001",
      "name": "Product A",
      "quantity": 10,
      "price": 100.0
    }
    ```
- **Response (201 Created)**:
    ```json
    {
      "message": "Item created successfully",
      "item": {
        "itemCode": "I00-001",
        "name": "Product A",
        "quantity": 10,
        "price": 100.0
      }
    }
    ```

#### 1.2 Update an Item
**Endpoint**: `PUT /api/v1/items/{itemCode}`

- **Description**: Updates the details of an existing item.
- **Request Body**:
    ```json
    {
      "name": "Product B",
      "price": 110.0,
      "quantity": 12
    }
    ```
- **Response (204 No Content)**:
    ```json
    {
      "message": "Item updated successfully",
      "item": {
        "itemCode": "I00-001",
        "name": "Product B",
        "quantity": 12,
        "price": 110.0
      }
    }
    ```

#### 1.3 Delete an Item
**Endpoint**: `DELETE /api/v1/items/{itemCode}`

- **Description**: Deletes a specific item from the inventory.
- **Response (204 No Content)**:
    ```json
    {
      "message": "Item deleted successfully"
    }
    ```

#### 1.4 Get a Specific Item
**Endpoint**: `GET /api/v1/items/{itemCode}`

- **Description**: Retrieves details of a specific item by its item code.
- **Response (200 OK)**:
    ```json
    {
      "itemCode": "I00-001",
      "name": "Product A",
      "quantity": 10,
      "price": 100.0
    }
    ```

#### 1.5 Get All Items
**Endpoint**: `GET /api/v1/items`

- **Description**: Retrieves all available items in the inventory.
- **Response (200 OK)**:
    ```json
    [
      {
        "itemCode": "I00-001",
        "name": "Product A",
        "quantity": 10,
        "price": 100.0
      },
      {
        "itemCode": "I00-002",
        "name": "Product B",
        "quantity": 5,
        "price": 150.0
      }
    ]
    ```

---

### 2. **Customers API**

#### 2.1 Create a New Customer
**Endpoint**: `POST /api/v1/customers`

- **Description**: Creates a new customer.
- **Request Body**:
    ```json
    {
      "customerId": "C00-001",
      "name": "John Doe",
      "city": "Colombo",
      "tel": "0771234567"
    }
    ```
- **Response (201 Created)**:
    ```json
    {
      "message": "Customer created successfully",
      "customer": {
        "customerId": "C00-001",
        "name": "John Doe",
        "city": "Colombo",
        "tel": "0771234567"
      }
    }
    ```

#### 2.2 Update a Customer
**Endpoint**: `PUT /api/v1/customers/{customerId}`

- **Description**: Update a customer.
- **Request Body**:
    ```json
    {
      "name": "John",
      "city": "Kaluthara",
      "tel": "0784562352"
    }
    ```
- **Response (204 No Content)**:
    ```json
    {
      "message": "Customer updated successfully",
      "customer": {
        "customerId": "C00-001",
        "name": "John",
        "city": "Kaluthara",
        "tel": "0784562352"
      }
    }
    ```

#### 2.3 Delete a Customer
**Endpoint**: `DELETE /api/v1/customers/{customerId}`

- **Description**: Deletes a specific customer.
- **Response (204 No Content)**:
    ```json
    {
      "message": "Customer deleted successfully"
    }
    ```

#### 2.4 Get a Specific Customer
**Endpoint**: `GET /api/v1/customers/{customerId}`

- **Description**: Retrieves details of a specific customer by their customer ID.
- **Response (200 OK)**:
    ```json
    {
      "customerId": "C00-001",
      "name": "John Doe",
      "city": "Colombo",
      "tel": "0771234567"
    }
    ```

#### 2.5 Get All Customers
**Endpoint**: `GET /api/v1/customers`

- **Description**: Retrieves all available customers.
- **Response (200 OK)**:
    ```json
    [
      {
        "customerId": "C00-001",
        "name": "John Doe",
        "city": "Colombo",
        "tel": "0771234567"
      },
      {
        "customerId": "C00-002",
        "name": "John",
        "city": "Kaluthara",
        "tel": "0784562352"
      }
    ]
    ```

---

### 3. **Orders API**

#### 3.1 Place an Order
**Endpoint**: `POST /api/v1/orders`

- **Description**: Places a new order.
- **Request Body**:
    ```json
    {
      "orderId": "O-01",
      "date": "2024-10-14",
      "discountRate": "5",
      "discount": "30",
      "subTotal": 600.0,
      "balance": 570.0,
      "customer": [
        {
          "customerId": "C00-001",
          "name": "John Doe",
          "city": "Colombo",
          "tel": "0771234567"
        }
      ],
      "orderDetailsList": [
        {
          "id": "ORDER_DETAIL-351582dsfds65sc46scs8s8",
          "date": "2024-10-14",
          "customerId": "C00-001",
          "customerName": "John Doe",
          "customerCity": "Colombo",
          "customerTel": "0771234567",
          "itemName": "Product A",
          "orderQTY": "6",
          "unitPrice": "100.0",
          "item": [
            {
              "itemCode": "I00-001",
              "name": "Product A",
              "quantity": 10,
              "price": 100.0
            }
          ],
          "order": [
            {
              "orderId": "O-01"
            }
          ]
        }
      ]
    }
    ```
- **Response (201 Created)**:
    ```json
    {
      "message": "Order placed successfully",
      "order": {
        "orderId": "O-01",
        "date": "2024-10-14",
        "discountRate": "5",
        "discount": "30",
        "subTotal": 600.0,
        "balance": 570.0
      }
    }
    ```

---

### 4. **User API**

#### 4.1 Create a User
**Endpoint**: `POST /api/v1/users`

- **Description**: Creates a new user for login purposes.
- **Request Body**:
    ```json
    {
      "username": "john_doe",
      "password": "password123"
    }
    ```
- **Response (201 Created)**:
    ```json
    {
      "message": "User created successfully",
      "user": {
        "username": "john_doe",
        "password": "password123"
      }
    }
    ```

#### 4.2 Get a Specific User
**Endpoint**: `GET /api/v1/users/{username}`

- **Description**: Retrieves a specific user's details.
- **Response (200 OK)**:
    ```json
    {
      "username": "john_doe",
      "password": "password123"
    }
    ```

---

### 5. **OrderDetails API**

#### 5.1 Create Order Detail
**Endpoint**: `POST /api/v1/orderdetails`

- **Description**: Creates a new order detail.
- **Request Body**:
    ```json
    {
      "orderId": "O-01",
      "itemCode": "I00-001",
      "quantity": 6,
      "unitPrice": 100.0
    }
    ```
- **Response (201 Created)**:
    ```json
    {
      "message": "Order detail created successfully",
      "orderDetail": {
        "orderId": "O-01",
        "itemCode": "I00-001",
        "quantity": 6,
        "unitPrice": 100.0
      }
    }
    ```

#### 5.2 Get Order Details by Order
**Endpoint**: `GET /api/v1/orderdetails/{orderId}`

- **Description**: Retrieves details for a specific order.
- **Response (200 OK)**:
    ```json
    [
      {
        "orderId": "O-01",
        "itemCode": "I00-001",
        "quantity": 6,
        "unitPrice": 100.0
      }
    ]
    ```
---

## Contributing
If you want to contribute to this project, please follow the steps below:
1. Fork the repository.
2. Clone your fork to your local machine.
3. Make changes and commit them to a new branch.
4. Submit a pull request for review.

---

## License
This project is licensed under the MIT License.
