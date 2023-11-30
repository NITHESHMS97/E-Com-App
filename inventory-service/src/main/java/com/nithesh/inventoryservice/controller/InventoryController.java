package com.nithesh.inventoryservice.controller;

import com.nithesh.inventoryservice.dto.InventoryRequest;
import com.nithesh.inventoryservice.dto.InventoryResponse;
import com.nithesh.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final InventoryService inventoryService;
  @GetMapping("/{sku-code}")
  @ResponseStatus(HttpStatus.OK)
  public boolean isInStock(@PathVariable("sku-code")String skuCode){
    return inventoryService.isInStock(skuCode);
  }
  @ResponseStatus(HttpStatus.OK)
  public List<InventoryResponse> isItemsInStock(@RequestParam List<String> skuCode){
    return inventoryService.isInStock(skuCode);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String addInventory(@RequestBody InventoryRequest inventoryRequest){
    return "Inventory added";
  }

}
