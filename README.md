# Simple Expense Tracker

A straightforward expense tracking application built with **Spring Boot** (back end) and **React** (front end), using **MySQL** as the database.

---

## Features

1. **User Authentication (JWT)**
   - Register and log in to obtain a JWT token.
   - Authenticate requests by including `Authorization: Bearer <token>` in headers.

2. **Expense Management**
   - **Create** new expenses.
   - **Read** all or specific expenses (optionally paginated via `?page=...&size=...`).
   - **Update** existing expenses.
   - **Delete** expenses.

---

## Tech Stack

- **Backend**: Spring Boot (Java)
- **Database**: MySQL
- **Frontend**: React (TypeScript)

---

## Usage

- **Log In / Register**: Acquire a JWT token upon successful login.
- **CRUD Operations**: Provide the token in the `Authorization` header to access protected endpoints (create, read, update, delete expenses).

---

## API Endpoints 

| Method | Endpoint                  | Description                 | Auth?      |
|-------:|---------------------------|-----------------------------|------------|
| **POST**   | `/auth/register`         | Register a new user        | No         |
| **POST**   | `/auth/login`            | Log in (get JWT)           | No         |
| **GET**    | `/expenses`              | Get all expenses (paged)   | Yes (JWT)  |
| **GET**    | `/expenses/{expenseId}`  | Get a specific expense     | Yes (JWT)  |
| **POST**   | `/expenses`              | Create a new expense       | Yes (JWT)  |
| **PUT**    | `/expenses/{expenseId}`  | Update an existing expense | Yes (JWT)  |
| **DELETE** | `/expenses/{expenseId}`  | Delete an expense          | Yes (JWT)  |



