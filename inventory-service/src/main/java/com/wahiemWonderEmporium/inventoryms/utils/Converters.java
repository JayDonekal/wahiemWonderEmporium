package com.wahiemWonderEmporium.inventoryms.utils;



import com.wahiemWonderEmporium.inventoryms.model.Inventory;
import com.wahiemWonderEmporium.inventoryms.model.viewModel.InventoryRequest;
import com.wahiemWonderEmporium.inventoryms.model.viewModel.InventoryResponse;
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
                .isInStock(inventory.getQuantity()>0?true:false)
                .build();
    }
}
