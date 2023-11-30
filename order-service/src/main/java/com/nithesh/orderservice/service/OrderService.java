package com.nithesh.orderservice.service;


import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import com.nithesh.orderservice.dto.InventoryResponse;
import com.nithesh.orderservice.dto.OrderLineItemsDto;
import com.nithesh.orderservice.dto.OrderRequest;
import com.nithesh.orderservice.model.Order;
import com.nithesh.orderservice.model.OrderLineItems;
import com.nithesh.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  private final OrderRepository orderRepository;
  private final WebClient webClient;
  public void placeOrder(OrderRequest orderRequest){
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
        .stream()
        .map(this::mapToDto)
        .toList();
    order.setOrderLineItemsList(orderLineItems);

    List<String> skuCodes =  order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode)
            .toList();

    InventoryResponse[] inventoryResponses =  webClient.get()
            .uri("http://localhost:8083/api/inventory",
                    uriBuilder -> uriBuilder.queryParam("skuCode",skuCodes).build())
                    .retrieve()
                            .bodyToMono(InventoryResponse[].class)
                                    .block();

    boolean allProductsInStock =   Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);

    if(allProductsInStock)
      orderRepository.save(order);
    else{
      throw new IllegalArgumentException("Product is not in stock, please try again later");
    }
  }

  private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
    return orderLineItems;
  }

}
