package com.nithesh.inventoryservice.service;

import com.nithesh.inventoryservice.model.Inventory;
import com.nithesh.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final InventoryRepository inventoryRepository;

  @Transactional
  public boolean isInStock(String skuCode){
      return inventoryRepository.findBySkuCode(skuCode).isPresent();
  }

  @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
    return args -> {
      Inventory inventory1 = new Inventory();
      inventory1.setSkuCode("iphone-13");
      inventory1.setQuantity(100);

      Inventory inventory2 = new Inventory();
      inventory2.setSkuCode("iphone-13-red");
      inventory2.setQuantity(0);

      inventoryRepository.save(inventory1);
      inventoryRepository.save(inventory2);
    };
  }
}
