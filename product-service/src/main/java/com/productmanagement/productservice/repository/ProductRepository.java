package com.productmanagement.productservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.productmanagement.productservice.model.Product;

public interface ProductRepository extends MongoRepository<Product, String>  {

}
