package com.abc.service;

import java.util.Set;

import com.abc.dto.ProductDTO;

public interface ProductService {

	ProductDTO saveProduct(ProductDTO product);
	
	ProductDTO findProductById(long productId);
	
	Set<ProductDTO> findAllProducts();
	
	ProductDTO updateProduct(ProductDTO product);
	
	void deleteProduct(long productId);
	
}
