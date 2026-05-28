package com.Haile_Peter_Abdisa;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

/**
 * Shared base class that starts ONE PostgreSQL container for all integration tests.
 * Using a static field on a shared base means the container starts once per JVM run,
 * not once per test class (much faster).
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers(disabledWithoutDocker = true)
public abstract class PostgresContainerBase {

    @Container
    static final PostgreSQLContainer<?> POSTGRES =
        new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("shopwave_test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureDataSource(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",      POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username",  POSTGRES::getUsername);
        registry.add("spring.datasource.password",  POSTGRES::getPassword);
        registry.add("spring.flyway.enabled",       () -> "true");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "validate");
    }
}
