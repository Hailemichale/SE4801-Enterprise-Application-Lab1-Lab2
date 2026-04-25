package com.Haile_Peter_Abdisa;

import com.Haile_Peter_Abdisa.model.Product;
import com.Haile_Peter_Abdisa.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ProductRepository repo;
    @Autowired ObjectMapper mapper;

    private Long savedId;

    @BeforeEach
    void setUp() {
        Product p = repo.save(
            new Product("Test Laptop", 999.0, 10, "Electronics"));
        savedId = p.getId();
    }

    // ── 200 OK — List all ─────────────────────────────────────
    @Test
    void getAll_returns200() throws Exception {
        mockMvc.perform(get("/api/v1/products"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(greaterThan(0))));
    }

    // ── 200 OK — Get by ID ────────────────────────────────────
    @Test
    void getById_returns200_whenExists() throws Exception {
        mockMvc.perform(get("/api/v1/products/" + savedId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name", is("Test Laptop")))
               .andExpect(jsonPath("$.category", is("Electronics")));
    }

    // ── 201 Created — POST ────────────────────────────────────
    @Test
    void create_returns201_withLocation() throws Exception {
        String body = """
            {"name":"Headphones","price":89.99,"stockQty":50,"category":"Audio"}
            """;
        mockMvc.perform(post("/api/v1/products")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(body))
               .andExpect(status().isCreated())
               .andExpect(header().exists("Location"))
               .andExpect(jsonPath("$.id", notNullValue()))
               .andExpect(jsonPath("$.name", is("Headphones")));
    }

    // ── 200 OK — PUT update ───────────────────────────────────
    @Test
    void update_returns200() throws Exception {
        String body = """
            {"name":"Pro Laptop","price":1299.0,"stockQty":5,"category":"Electronics"}
            """;
        mockMvc.perform(put("/api/v1/products/" + savedId)
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(body))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name", is("Pro Laptop")))
               .andExpect(jsonPath("$.price", is(1299.0)));
    }

    // ── 204 No Content — DELETE ───────────────────────────────
    @Test
    void delete_returns204() throws Exception {
        mockMvc.perform(delete("/api/v1/products/" + savedId))
               .andExpect(status().isNoContent());
    }

    // ── 404 Not Found — GET unknown ID ───────────────────────
    @Test
    void getById_returns404_whenNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/products/9999"))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.detail", containsString("9999")));
    }

    // ── 404 Not Found — DELETE unknown ID ────────────────────
    @Test
    void delete_returns404_whenNotFound() throws Exception {
        mockMvc.perform(delete("/api/v1/products/9999"))
               .andExpect(status().isNotFound());
    }

    // ── 400 Bad Request — Validation failure ─────────────────
    @Test
    void create_returns400_whenNameBlank() throws Exception {
        String invalid = """
            {"name":"","price":10.0,"stockQty":1,"category":"Tech"}
            """;
        mockMvc.perform(post("/api/v1/products")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(invalid))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.detail", containsString("Name is required")));
    }

    // ── 400 Bad Request — Negative price ─────────────────────
    @Test
    void create_returns400_whenPriceInvalid() throws Exception {
        String invalid = """
            {"name":"Widget","price":-1,"stockQty":1,"category":"Tech"}
            """;
        mockMvc.perform(post("/api/v1/products")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(invalid))
               .andExpect(status().isBadRequest());
    }
}
