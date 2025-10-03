package com.wahiemwonderemporium.ordersms.model.viewModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {

    private Long id;
    private String skuCode;
    private Integer quantity;
    private boolean isInStock;
}
