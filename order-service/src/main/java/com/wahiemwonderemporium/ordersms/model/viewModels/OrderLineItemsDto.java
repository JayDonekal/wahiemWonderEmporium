package com.wahiemwonderemporium.ordersms.model.viewModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItemsDto {

    private String skuCode;
    private float price;
    private Integer quantity;
}
