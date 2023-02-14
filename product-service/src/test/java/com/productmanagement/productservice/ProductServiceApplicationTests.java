package com.productmanagement.productservice;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.productmanagement.productservice.model.Product;
import com.productmanagement.productservice.repository.ProductRepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	@Autowired
	MockMvc mockMvc;
	Product productRequest = getProductRequest();
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	ProductRepository productRepository;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void testCreateProduct() throws Exception {
		String jsonString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonString))
				.andExpect(MockMvcResultMatchers.status().isCreated());
		// System.out.println("productRepository");
		// productRepository.findAll().forEach((p) -> System.out.println(p));
		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private Product getProductRequest() {
		return Product.builder().name("Iphone 14").description("Iphone 14").price(BigDecimal.valueOf(90000)).build();
	}

	@Test
	void testGetProduct() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(""))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Assertions.assertEquals(1, productRepository.findAll().size());
	}

}
