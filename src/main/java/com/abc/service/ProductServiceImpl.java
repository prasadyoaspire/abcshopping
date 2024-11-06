package com.abc.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.dto.ProductDTO;
import com.abc.entity.Product;
import com.abc.exception.ResourceNotFoundException;
import com.abc.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDTO saveProduct(ProductDTO product) {
		
		//convert dto to entity
		Product productEntity = modelMapper.map(product, Product.class);		
		
		Product newProduct = productRepository.save(productEntity);
		
		//convert entity to dto
		product = modelMapper.map(newProduct, ProductDTO.class);
		
		return product;
	}

	@Override
	public ProductDTO findProductById(long productId) throws ResourceNotFoundException{
		
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isEmpty()) {
			throw new ResourceNotFoundException("Produt not found with id: "+productId);
		}
		
		Product product = optionalProduct.get();
		
		//convert entity to dto
		ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
	
		return productDTO;
	}

	@Override
	public Set<ProductDTO> findAllProducts() {
	
		List<Product> products = productRepository.findAll();
		return products.stream().map(product -> modelMapper.map(product, ProductDTO.class)).collect(Collectors.toSet());
	}

	@Override
	public ProductDTO updateProduct(ProductDTO product) {
		
		Optional<Product> optionalProduct = productRepository.findById(product.getId());
		if(optionalProduct.isEmpty()) {
			throw new ResourceNotFoundException("Produt not found with id: "+product.getId());
		}
			
		Product productEntity = modelMapper.map(product, Product.class);	
		
		productRepository.save(productEntity);
		
		product = modelMapper.map(productEntity, ProductDTO.class);
		
		return product;
	}

	@Override
	public void deleteProduct(long productId) {
		
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(optionalProduct.isEmpty()) {
			throw new ResourceNotFoundException("Produt not found with id: "+productId);
		}
		
		Product product = optionalProduct.get();
		
		productRepository.delete(product);
	}

}
