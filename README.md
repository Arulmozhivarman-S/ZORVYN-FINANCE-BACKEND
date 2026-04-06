# Finance Data Processing & Access Control Backend


## Overview

This project is a backend system for managing financial data such as transactions, users, and role-based access.

It supports:
- User management with roles (ADMIN, ANALYST, VIEWER)
- Financial transaction CRUD operations
- Role-based access control (RBAC)
- Pagination and filtering of transactions

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

## Role-Based Access Control

The system supports three roles:

- ADMIN:
  - Create, update, delete users
  - Full access to transactions

- ANALYST:
  - View transactions
  - Access summary APIs

- VIEWER:
  - Read-only access

Access control is enforced at the service layer using role checks.


## 📊 Dashboard API

GET /dashboard/{userId}?currentUserId=1

This API returns aggregated financial insights in a single response.

### Response includes:
- Total Income
- Total Expenses
- Net Balance
- Category-wise totals
- Recent transactions


## User APIs

POST /users?currentUserId=1
- Create a new user (ADMIN only)

GET /users/{userId}?currentUserId=1
- Get user details

PUT /users/{id}?currentUserId=1
- Update user

DELETE /users/{id}?currentUserId=1
- Delete user (ADMIN only)

## Transaction APIs

POST /transactions/{userId}
- Add transaction

GET /transactions/{userId}?page=0&size=10
- Get paginated transactions

PUT /transactions/{transactionId}
- Update transaction

DELETE /transactions/{transactionId}
- Delete transaction



## Data Model

User:
- userId
- email
- password
- userRole (ADMIN, ANALYST, VIEWER)
- userStatus (ACTIVE, INACTIVE)

Transaction:
- transactionId
- amount
- transactionType (INCOME, EXPENSE)
- category
- createdAt
- description
- user (ManyToOne)

## Validation & Error Handling

- Input validation using annotations:
  - @NotNull
  - @NotBlank
  - @Email
  - @Positive

- Custom Exceptions:
  - ResourceNotFoundException
  - InvalidAccessException

- Proper HTTP status codes are returned:
  - 200 OK
  - 201 CREATED
  - 400 BAD REQUEST
  - 404 NOT FOUND
  - 403 FORBIDDEN


  ## Tradeoffs

- Used manual role checking instead of Spring Security for simplicity
- Simple User DTO layer used to reduce complexity
- No caching or performance optimizations added


## Future Improvements

- Add JWT-based authentication
- Implement @PreAuthorize with Spring Security
- Add global exception handling
- Add unit and integration tests
