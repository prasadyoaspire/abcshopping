package com.abc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.abc.dto.ProductDTO;
import com.abc.entity.Product;
import com.abc.exception.ResourceNotFoundException;
import com.abc.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepository;
	
//	@Mock
//	private ModelMapper modelMapper;
	
	@Spy
	private ModelMapper modelMapper;
	

	@Test
	@Disabled
	void testFindProductById() {
		
		Long productId = 10L;
		
		Product product = new Product();
		product.setId(10L);
		product.setProductName("IPHoneX+");
		product.setProductPrice(50000);
		product.setMfd(LocalDate.of(2023, 12, 10));
		product.setCategory("mobile");
		
		Optional<Product> optionalProduct = Optional.of(product);
		
		when(productRepository.findById(productId)).thenReturn(optionalProduct);
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(10L);
		productDTO.setProductName("IPHoneX+");
		productDTO.setProductPrice(50000);
		productDTO.setMfd(LocalDate.of(2023, 12, 10));
		productDTO.setCategory("mobile");
		
		when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);
		
		productDTO = productService.findProductById(productId);
		
		assertEquals(product.getProductName(),productDTO.getProductName());
		assertEquals(product.getProductPrice(),productDTO.getProductPrice());
		
	}	
	
	
	@Test
	void findProductByIdWithException() {
		
		when(productRepository.findById(10L)).thenThrow(ResourceNotFoundException.class);
		
		assertThrows(ResourceNotFoundException.class,()->productService.findProductById(10L));
	}
		
	
	@Test
	void testFindAllProducts() {
		
		Product product1 = new Product();
		product1.setId(10L);
		product1.setProductName("IPHoneX+");
		product1.setProductPrice(50000);
		product1.setMfd(LocalDate.of(2023, 12, 10));
		product1.setCategory("mobile");
		
		Product product2 = new Product();
		product2.setId(20L);
		product2.setProductName("IPHoneY+");
		product2.setProductPrice(50000);
		product2.setMfd(LocalDate.of(2023, 12, 10));
		product2.setCategory("mobile");
		
		Product product3 = new Product();
		product3.setId(30L);
		product3.setProductName("IPHoneZ+");
		product3.setProductPrice(50000);
		product3.setMfd(LocalDate.of(2023, 12, 10));
		product3.setCategory("mobile");
		
		List<Product> productList = new ArrayList<>();
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		
		when(productRepository.findAll()).thenReturn(productList);
		
		Set<ProductDTO> productDtos = productService.findAllProducts();
		
		assertEquals(3,productDtos.size());
	}

	
	@Test
	void testSaveProduct() {
		
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(10L);
		productDTO.setProductName("IPHoneX+");
		productDTO.setProductPrice(50000);
		productDTO.setMfd(LocalDate.of(2023, 12, 10));
		productDTO.setCategory("mobile");
		
		Product product = new Product();
		product.setId(10L);
		product.setProductName("IPHoneX+");
		product.setProductPrice(50000);
		product.setMfd(LocalDate.of(2023, 12, 10));
		product.setCategory("mobile");
		
		when(productRepository.save(product)).thenReturn(product);
		
		productDTO = productService.saveProduct(productDTO);
		
		assertEquals("IPHoneX+",productDTO.getProductName());
	
	}

	@Test
	void testDeleteProduct() {
		
		Product product = new Product();
		product.setId(10L);
		product.setProductName("IPHoneX+");
		product.setProductPrice(50000);
		product.setMfd(LocalDate.of(2023, 12, 10));
		product.setCategory("mobile");
		
		Optional<Product> optionalProduct = Optional.of(product);
		
		when(productRepository.findById(10L)).thenReturn(optionalProduct);	
		
		
		productService.deleteProduct(10L);
		
		verify(productRepository,times(1)).delete(optionalProduct.get());
	}

}
