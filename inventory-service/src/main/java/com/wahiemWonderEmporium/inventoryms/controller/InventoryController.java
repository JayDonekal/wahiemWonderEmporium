package com.wahiemWonderEmporium.inventoryms.controller;

import com.wahiemWonderEmporium.inventoryms.model.viewModel.InventoryRequest;
import com.wahiemWonderEmporium.inventoryms.model.viewModel.InventoryResponse;
import com.wahiemWonderEmporium.inventoryms.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCodes) {
        return inventoryService.isInStock(skuCodes);
    }

    @GetMapping("/all")
    public List<InventoryResponse> getAllInventory() {
        return inventoryService.getAllInventory();
    }


    @PostMapping
    public ResponseEntity<InventoryResponse> addNewInventory(@RequestBody InventoryRequest inventoryRequest) {

        return new ResponseEntity<>(inventoryService.addNewInventory(inventoryRequest), HttpStatus.CREATED);
    }

}
