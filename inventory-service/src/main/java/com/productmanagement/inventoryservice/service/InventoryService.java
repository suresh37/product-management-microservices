package com.productmanagement.inventoryservice.service;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.productmanagement.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepo;

	@Transactional(readOnly = true)
	public Boolean isInStock(String skuCode) {
		return inventoryRepo.findBySkuCode(skuCode).isPresent();
	}

}
