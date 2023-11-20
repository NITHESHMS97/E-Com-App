package com.nithesh.inventoryservice.repository;

import com.nithesh.inventoryservice.model.Inventory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

  Optional<Inventory> findBySkuCode(String skuCode);
}
