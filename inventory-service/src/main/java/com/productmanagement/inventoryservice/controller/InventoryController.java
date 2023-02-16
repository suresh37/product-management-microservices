package com.productmanagement.inventoryservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.productmanagement.inventoryservice.model.InventoryDto;
import com.productmanagement.inventoryservice.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

	private final InventoryService inventoryService;

	@GetMapping("/isinstock")
	@ResponseStatus(HttpStatus.OK)
	public List<InventoryDto> isInStock(@RequestParam List<String> skuCodes) {
		return inventoryService.isInStock(skuCodes);
	}

}
