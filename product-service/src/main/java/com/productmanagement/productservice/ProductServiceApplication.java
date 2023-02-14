package com.productmanagement.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
		
		// Product p = new Product("1","Pen","Butterflow",BigDecimal.valueOf(15));
		// System.out.println("Product;Pen");
		// p.toString();
	}

}
