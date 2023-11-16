package com.nithesh.orderservice.service;


import static java.util.stream.Collectors.toList;

import com.nithesh.orderservice.dto.OrderLineItemsDto;
import com.nithesh.orderservice.dto.OrderRequest;
import com.nithesh.orderservice.model.Order;
import com.nithesh.orderservice.model.OrderLineItems;
import com.nithesh.orderservice.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  public void placeOrder(OrderRequest orderRequest){
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());
    List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
        .stream()
        .map(this::mapToDto)
        .toList();
    order.setOrderLineItemsList(orderLineItems);
    orderRepository.save(order);

  }

  private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
    return orderLineItems;
  }

}
