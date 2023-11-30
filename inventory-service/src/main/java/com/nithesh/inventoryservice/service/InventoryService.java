package com.nithesh.inventoryservice.service;

import com.nithesh.inventoryservice.dto.InventoryResponse;
import com.nithesh.inventoryservice.model.Inventory;
import com.nithesh.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

  private final InventoryRepository inventoryRepository;

  @Transactional
  public boolean isInStock(String skuCode){
      return inventoryRepository.findBySkuCode(skuCode).isPresent();
  }
  public List<InventoryResponse> isInStock(List<String> skuCodes) {
    return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
            .map(inventory ->
              InventoryResponse.builder()
                      .skuCode(inventory.getSkuCode())
                      .isInStock(inventory.getQuantity() > 0)
                      .build()
            ).toList();
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
