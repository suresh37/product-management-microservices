package com.productmanagement.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.productmanagement.productservice.model.Product;
import com.productmanagement.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	// @Autowired
	private final ProductRepository productRepo;

	public List<Product> getProducts() {
		return productRepo.findAll();
	}

	public Product getProduct(String id) {
		Product product = productRepo.findById(id).orElse(null);
		return product;
	}

	public Product createProduct(Product productRequest) {
		Product product = Product.builder().name(productRequest.getName()).description(productRequest.getDescription())
				.price(productRequest.getPrice()).build();
		productRepo.save(product);
		return product;
	}

	public Product updateProduct(String id, Product productRequest) {
		 Product product = productRepo.findById(id).orElse(null);
		 productRepo.save(productRequest);
		return product;
	}

}
