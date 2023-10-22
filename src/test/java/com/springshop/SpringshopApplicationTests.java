package com.springshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springshop.domains.product.ProductRepository;
import com.springshop.openapi.model.ProductCreateRequest;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Testcontainers
@RequiredArgsConstructor
@AutoConfigureMockMvc
class SpringshopApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:15")
            .withDatabaseName("integration-tests-db")
            .withUsername("sa")
            .withPassword("sa");

    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
    }

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dpr) {
        dpr.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        dpr.add("spring.datasource.username", postgreSQLContainer::getUsername);
        dpr.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getProductJSONRequest());

        mockMvc.perform(request)
               .andExpect(status().isCreated());

        Assertions.assertEquals(1, productRepository.count());
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        MockHttpServletRequestBuilder request1 = MockMvcRequestBuilders
                .post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getProductJSONRequest());

        MockHttpServletRequestBuilder request2 = MockMvcRequestBuilders
                .post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getProductJSONRequest());

        MockHttpServletRequestBuilder request3 = MockMvcRequestBuilders
                .get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request1);
        mockMvc.perform(request2);
        mockMvc.perform(request3).andExpect(status().isOk());

        Assertions.assertEquals(2, productRepository.count());
    }

    private String getProductJSONRequest() throws JsonProcessingException {
        ProductCreateRequest productCreateRequest = ProductCreateRequest
                .builder()
                .name("Product 1")
                .price(10.0)
                .description("Description 1")
                .categoryIds(new ArrayList<>())
                .build();

        return objectMapper.writeValueAsString(productCreateRequest);
    }

}
