package com.nithesh.orderservice.dto;

import com.nithesh.orderservice.model.OrderLineItems;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
  private List<OrderLineItemsDto> orderLineItemsDtoList;

}
