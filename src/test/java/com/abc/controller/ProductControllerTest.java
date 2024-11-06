package com.abc.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.abc.dto.ProductDTO;
import com.abc.exception.ResourceNotFoundException;
import com.abc.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@MockBean
	private ProductService productService;

	@Autowired
	MockMvc mockMvc;
	
//	@Autowired
//	private ObjectMapper objectMapper;
	
	@Test
	public void testFetchAllProducts() throws Exception {
		ProductDTO product1 = new ProductDTO(1L, "product-A", 5000.0, LocalDate.of(2014, 10, 10), null);
		ProductDTO product2 = new ProductDTO(2L, "product-B", 5000.0, LocalDate.of(2014, 10, 10), null);
		ProductDTO product3 = new ProductDTO(3L, "product-C", 5000.0, LocalDate.of(2014, 10, 10), null);

		Set<ProductDTO> products = new LinkedHashSet<>();
		products.add(product1);
		products.add(product2);
		products.add(product3);

		when(productService.findAllProducts()).thenReturn(products);

//		mockMvc.perform(get("/product/")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(3)))
//				.andExpect(jsonPath("$[0].name", Matchers.is("project-C")));
		
		mockMvc.perform(get("/product/"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(3)))
		.andExpect(jsonPath("$[0].productName", Matchers.is("product-A")))
		.andExpect(jsonPath("$[1].productName", Matchers.is("product-B")));
	
	}
	
	@Test
	public void testFetchProductDetails() throws Exception {

		Long id = 1L;

		ProductDTO product1 = new ProductDTO(1L, "product-A", 5000.0, LocalDate.of(2014, 10, 10), null);

		when(productService.findProductById(id)).thenReturn(product1);

		mockMvc.perform(get("/product/{projectId}", id)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id))
				.andExpect(jsonPath("$.productName").value(product1.getProductName()))
				.andExpect(jsonPath("$.productPrice").value(product1.getProductPrice()))
				.andExpect(jsonPath("$.mfd").value(product1.getMfd().toString()))
				.andExpect(jsonPath("$.category").value(product1.getCategory())).andDo(print());
	}
	
	@Test
	void shouldReturnNotFoundProduct() throws Exception {
		long id = 1L;
		when(productService.findProductById(id)).thenThrow(ResourceNotFoundException.class);
		mockMvc.perform(get("/product/{projectId}", id)).andExpect(status().isNotFound())
				.andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
				.andDo(print());
	}
	
	@Test
	void testRemoveProduct() throws Exception {
		long id = 1L;
		doNothing().when(productService).deleteProduct(id);	
		
//		when(productService.deleteProduct(id)).thenReturn(true);

		mockMvc.perform(delete("/product/{projectId}", id))
				.andExpect(status().isOk())
				.andDo(print());
		
	}
}
