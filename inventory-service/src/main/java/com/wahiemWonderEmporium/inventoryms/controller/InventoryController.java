package com.wahiemwonderemporium.inventoryms.controller;

import com.wahiemwonderemporium.inventoryms.model.Inventory;
import com.wahiemwonderemporium.inventoryms.model.viewModel.InventoryRequest;
import com.wahiemwonderemporium.inventoryms.model.viewModel.InventoryResponse;
import com.wahiemwonderemporium.inventoryms.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.wahiemwonderemporium.inventoryms.utils.Converters.mapToInventoryResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{skuCode}")
    public boolean isInStock(@PathVariable(value = "skuCode") String skuCode) {
        return inventoryService.isInStock(skuCode);
    }

    @PostMapping
    public ResponseEntity<InventoryResponse> addNewInventory(@RequestBody InventoryRequest inventoryRequest) {

        return new ResponseEntity<>(inventoryService.addNewInventory(inventoryRequest), HttpStatus.CREATED);
    }

}
