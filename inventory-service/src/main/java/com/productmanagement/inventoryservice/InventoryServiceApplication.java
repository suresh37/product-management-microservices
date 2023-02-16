package com.productmanagement.inventoryservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.productmanagement.inventoryservice.model.Inventory;
import com.productmanagement.inventoryservice.repository.InventoryRepository;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

    /*@Bean
    CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inv = new Inventory();
            inv.setSkuCode("Iphone 14");
            inv.setQuantity(10);
            inventoryRepository.save(inv);
            Inventory inv1 = new Inventory();
            inv1.setSkuCode("Iphone 13");
            inv1.setQuantity(15);
            inventoryRepository.save(inv1);
        };
    }*/

}
