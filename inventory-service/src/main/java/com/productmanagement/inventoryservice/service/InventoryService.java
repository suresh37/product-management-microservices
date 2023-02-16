package com.productmanagement.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.productmanagement.inventoryservice.model.Inventory;
import com.productmanagement.inventoryservice.model.InventoryDto;
import com.productmanagement.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepo;

	@Transactional(readOnly = true)
	public List<InventoryDto> isInStock(List<String> skuCodes) {
		List<InventoryDto> list = inventoryRepo.findBySkuCodeIn(skuCodes).stream().map(this::mapToDto).toList();
		return list;
	}

	private InventoryDto mapToDto(Inventory inv) {
		return InventoryDto.builder().skuCode(inv.getSkuCode()).isInStock(inv.getQuantity() > 0).build();
	}
}
