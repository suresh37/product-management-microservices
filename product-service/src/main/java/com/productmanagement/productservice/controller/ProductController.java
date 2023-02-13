package com.productmanagement.productservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagement.productservice.model.Product;
import com.productmanagement.productservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
	
	//@Autowired
	private final ProductService productService;

	@GetMapping(value = "")
	@ResponseStatus( value = HttpStatus.OK)
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus( value = HttpStatus.OK)
	public Product getProduct(@PathVariable String id) {
		return productService.getProduct(id);
	}
	
	@PostMapping
	@ResponseStatus( value = HttpStatus.CREATED)
	public Product createPoduct(@RequestBody Product product) {
		return productService.createProduct(product);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseStatus( value = HttpStatus.OK)
	public Product updateProduct(@PathVariable String id,@RequestBody Product product) {
		return productService.updateProduct(id,product);
	}

}
