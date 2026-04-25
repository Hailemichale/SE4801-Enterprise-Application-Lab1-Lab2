# Reflection Questions

1. **Why should the ProductRequest DTO carry the @Valid annotations instead of the Product entity itself?**
   Keeping validation annotations on the DTO decouples the API layer from the persistence layer. This ensures that validation specific to incoming HTTP requests doesn't pollute the domain model, allowing the entity to simply reflect the database schema while the DTO strictly enforces API-level constraints. It also enables us to expose only the required fields to the client, keeping fields like `id` safe from manipulation.

2. **What is the purpose of the Location header returned on a POST 201 Created response, and which HTTP specification mandates it?**
   The `Location` header provides the exact URI where the newly created resource can be retrieved. This follows REST conventions (and RFC 7231, HTTP/1.1 Semantics and Content), which states that returning a 201 Created should include a Location header identifying the location of the newly created resource.

3. **Explain the difference between @ControllerAdvice and @ExceptionHandler. When would you use each?**
   `@ExceptionHandler` is used at the controller level to handle specific exceptions thrown by methods in that exact controller. `@ControllerAdvice` is a global interceptor that handles exceptions across the entire application (multiple controllers). You would use `@ControllerAdvice` for common logic, like transforming standard exceptions (e.g., `ResourceNotFoundException`) into `ProblemDetail` responses, handling it globally instead of duplicating `@ExceptionHandler` in every controller.

4. **In your MockMvc tests you used @Transactional on the test class. What would happen to the database state between tests if you removed this annotation?**
   Without `@Transactional`, database changes (like saving or deleting a product) would persist across test executions, causing test state to leak. This would lead to tests interfering with each other and randomly failing due to unexpected pre-existing database states. Running tests in a transaction ensures that the database is rolled back to a clean state after every test.

5. **What does RFC 9457 define, and why does following it produce better APIs than returning a generic { error: 'something went wrong' } message?**
   RFC 9457 (Problem Details for HTTP APIs) provides a standardized XML/JSON format for reporting errors back to HTTP clients. This standardized schema structure (containing type, title, status, detail, instance) allows developers and clients to uniformly identify the errors, making the API far easier to integrate and handle programmatically as opposed to arbitrary string error messages.

6. **What is the difference between an integration test (MockMvc) and a unit test (Mockito)? When is each approach preferable?**
   Integration tests (using MockMvc & @SpringBootTest) bring up the Spring application context and test the synergy between multiple layers (controller > service > repo) handling database execution, routing, validation, and JSON serialization. A unit test (using Mockito) isolates a single class (such as the `ProductService`) by mocking its dependencies, running rapidly without starting the Spring context. Unit tests are preferable for testing complex business logic in isolation quickly, whereas integration tests are essential for ensuring the framework wiring, validation annotations, and SQL dialects function as expected.
