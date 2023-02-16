package com.productmanagement.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
		
		// Product p = new Product("1","Pen","Butterflow",BigDecimal.valueOf(15));
		// System.out.println("Product;Pen");
		// p.toString();
	}

}
