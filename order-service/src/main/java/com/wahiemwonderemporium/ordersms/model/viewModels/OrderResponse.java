package com.wahiemwonderemporium.ordersms.model.viewModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

    private long id;
    private String orderNumber;
    private List<OrderLineItemsDto> orderLineItemsDtoList;

}
