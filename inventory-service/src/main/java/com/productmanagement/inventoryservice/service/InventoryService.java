package com.productmanagement.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.productmanagement.inventoryservice.model.Inventory;
import com.productmanagement.inventoryservice.model.InventoryDto;
import com.productmanagement.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

	private final InventoryRepository inventoryRepo;

	@Transactional(readOnly = true)
	@SneakyThrows
	public List<InventoryDto> isInStock(List<String> skuCodes) {
		log.info("Wait Started");
		Thread.sleep(10000);
		log.info("Wait Ended");
		List<InventoryDto> list = inventoryRepo.findBySkuCodeIn(skuCodes).stream().map(this::mapToDto).toList();
		return list;
	}

	private InventoryDto mapToDto(Inventory inv) {
		return InventoryDto.builder().skuCode(inv.getSkuCode()).isInStock(inv.getQuantity() > 0).build();
	}
}
