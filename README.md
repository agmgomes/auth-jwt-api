
# Authentication API

This project is a Spring Boot-based REST API that provides user authentication
and registration functionalities using JWT (Json Web Tockens).
It includes secure login, user registration, and role-based access control.

The project make use of **Spring Boot**, **Spring Security**,
**PostegreSQL** as the database, and **Flyway** for datase migrations.

## Requirements

- **Java**: 17 or higher
- **Maven**: 3.8.7 or higher
- **Docker** and **Docker Compose**: for use PostregreSQL database or use your own
- **Insomnia** (optional): For testing API endpoints

## Installation
1. Clone the repository:
```bash
git clone https://github.com/agmgomes/auth-jwt-api.git
cd auth-jwt-api
```

2. Environment setup:

- **Option 1** - Change the properties at `src/resources/application.properties`
if using your own PostegreSQL database.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/authjwtdb
spring.datasource.username=username
spring.datasource.password=password

api.security.jwt.secret=jwtsecret
```

- **Option 2** - Using Docker Compose:

```bash
cd docker
docker-compose up -d
```

3. Build and run:
```bash
mvn clean install
mvn spring-boot:run
```

The API will be accessible locally by `http://localhost:8080`

## API Endpoints

### Authentication
| Method | Endpoint       | Description              | Authorization |
|:-------|:---------------|:-------------------------|:--------------|
| POST   | /auth/register | Register a new User      | None          |
| POST   | /auth/login    | Authenticate and get JWT | None          |

### Protected resources
| Method | Endpoint   | Description        | Authorization             |
|:-------|:-----------|:-------------------|:--------------------------|
| POST   | /tasks     | Create a new task  | Bearer Token              |
| GET    | /tasks     | Get all user tasks | Bearer Token              |
| GET    | /tasks/all | Get all tasks      | Bearer Token - Role ADMIN |

## Authentication Flow
1. **User Registration**:
- Endpoint: `POST /auth/register`
- A new user provides login and password to register.
```bash
POST /auth/register
Content-type: application/json
{
  "login": "user",
  "password": "securepassword",
  "role": "ADMIN" (or "role": "USER", choose depending on the testing case)
}
```

2. **Login**:
- Endpoint: `POST /auth/login`
- Returns a JWT if the provided credentials are valid.
```bash
POST /auth/login
Content-type: application/json
{
  "login": "user",
  "password": "securepassword"
}
```

3. **Access Protected Resources**:
- Use the JWT as a Bearer Token into the `Authorization` heard for subsequent requests:
```bash
Authorization: Bearer <JWT_TOKEN>
```

## Testing
1. Use Insomnia or another API client of your choice to test the endpoints.
2. Example test case for creating a task:
```bash
POST /tasks
Content-type: application/json
{
  "title": "Task title",
  "description": "Task description"
}
```

## Development Notes

### Technologies Used
- **Spring Boot**: Core framework for building the application.
- **Spring Security**: For authentication and authorization.
- **PostegreSQL**: Database for storing user information and tasks.
- **Flyway**: For managing and versioning database migrations.
- **JWT**: For token-based authorization.
- **Docker Compose**: Used to set up and manage the database environment.

### Custom Filters
- The application uses a custom `JwtFilter` to validate tokens and extract user information 
from the `Authorization` header.

### **Centralized Exception Handling**
- **Authentication and Access Control Errors**:
    - **401 Unauthorized**: No token or an invalid token provided.
    - **403 Forbidden**: Insufficient permissions.
- **Validation Errors**:
    - **400 Bad Request**: Invalid or incomplete request body.
    - **409 Conflict**: Attempt to create a resource that already exists (e.g., duplicate login).
- **Resource Errors**:
    - **404 Not Found**: returned when no tasks found in the database.
- Errors return a JSON object with details about the issue.

## LICENSE
This project is licensed under the MIT License - see the [LICENSE](./LICENSE) file for details.