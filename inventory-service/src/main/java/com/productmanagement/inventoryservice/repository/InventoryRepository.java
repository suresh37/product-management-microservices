package com.productmanagement.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productmanagement.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public Optional<Inventory> findBySkuCode(String skuCode);

    public List<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
