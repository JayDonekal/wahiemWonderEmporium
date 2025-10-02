package com.wahiemwonderemporium.inventoryms.utils;


import com.wahiemwonderemporium.inventoryms.model.Inventory;
import com.wahiemwonderemporium.inventoryms.model.viewModel.InventoryRequest;
import com.wahiemwonderemporium.inventoryms.model.viewModel.InventoryResponse;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.UtilityClass;

@UtilityClass
@Data
@Builder
public class Converters {

    public static Inventory mapToInventory(InventoryRequest inventoryRequest){
        return Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
    }

    public static InventoryResponse mapToInventoryResponse(Inventory inventory){
        return InventoryResponse.builder()
                .id(inventory.getId())
                .skuCode(inventory.getSkuCode())
                .quantity(inventory.getQuantity())
                .build();
    }
}
