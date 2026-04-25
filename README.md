# SE4801 – Enterprise Application Development
## Lab 1 & Lab 2: RESTful Product Catalogue API

---

| Name | ID |
|------|-----|
| Hailemichale Lijalem | ATE/1051/14 |

---

### Project Overview

This project is a Spring Boot RESTful API for managing a product catalogue. It was built as part of the Lab 1 and Lab 2 assignments for the SE4801 Enterprise Application Development course.

The API supports full CRUD operations on products and follows REST best practices including proper HTTP status codes, a `Location` header on creation, and RFC 9457 `ProblemDetail` error responses.

---

### Technologies Used

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA** (H2 in-memory database)
- **Spring Validation** (`@Valid`, `@NotBlank`, etc.)
- **SpringDoc OpenAPI / Swagger UI**
- **MockMvc** (integration testing)
- **GitHub Actions** (CI pipeline)

---

### Project Structure

```
src/
├── main/
│   ├── java/com/Haile_Peter_Abdisa/
│   │   ├── LAb1Application.java          # Entry point
│   │   ├── controller/
│   │   │   └── ProductController.java    # REST endpoints
│   │   ├── service/
│   │   │   └── ProductService.java       # Business logic
│   │   ├── repository/
│   │   │   └── ProductRepository.java    # JPA repository
│   │   ├── model/
│   │   │   └── Product.java              # JPA entity
│   │   ├── dto/
│   │   │   ├── ProductRequest.java       # Input DTO with validation
│   │   │   └── ProductResponse.java      # Output DTO
│   │   └── exception/
│   │       ├── GlobalExceptionHandler.java
│   │       └── ResourceNotFoundException.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/Haile_Peter_Abdisa/
        ├── LAb1ApplicationTests.java
        └── ProductControllerTest.java    # MockMvc integration tests
```

---

### How to Run

**Prerequisites:** Java 21 and Maven installed.

```bash
# Clone the repository
git clone https://github.com/Hailemichale/SE4801-Enterprise-Application-Lab1-Lab2.git
cd SE4801-Enterprise-Application-Lab1-Lab2

# Run the application
./mvnw spring-boot:run
```

The application starts on **http://localhost:8080**

---

### API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/products` | Get all products |
| `GET` | `/api/products/{id}` | Get a product by ID |
| `POST` | `/api/products` | Create a new product |
| `PUT` | `/api/products/{id}` | Update an existing product |
| `DELETE` | `/api/products/{id}` | Delete a product |

#### Example Request Body (POST / PUT)

```json
{
  "name": "Laptop",
  "description": "A high performance laptop",
  "price": 1200.00,
  "category": "Electronics"
}
```

---

### Swagger UI

After starting the app, open your browser and go to:

```
http://localhost:8080/swagger-ui/index.html
```

---

### Running Tests

```bash
./mvnw test
```

Tests use MockMvc with `@SpringBootTest` and `@Transactional` to ensure each test runs in isolation with a clean database state.

---

### H2 Database Console

```
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave blank)
```

---

### CI/CD

A GitHub Actions pipeline is configured under `.github/workflows/ci.yml`. It automatically runs `mvn test` on every push and pull request to the `master` branch.

---

### Postman Collection

A Postman collection is included under the `postman/` directory for manual API testing.

---

*SE4801 Enterprise Application Development – Addis Ababa University*
