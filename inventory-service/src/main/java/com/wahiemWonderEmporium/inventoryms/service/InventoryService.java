package com.wahiemwonderemporium.inventoryms.service;

import com.wahiemwonderemporium.inventoryms.model.Inventory;
import com.wahiemwonderemporium.inventoryms.model.viewModel.InventoryRequest;
import com.wahiemwonderemporium.inventoryms.model.viewModel.InventoryResponse;
import com.wahiemwonderemporium.inventoryms.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import static com.wahiemwonderemporium.inventoryms.utils.Converters.mapToInventory;
import static com.wahiemwonderemporium.inventoryms.utils.Converters.mapToInventoryResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    public final InventoryRepository inventoryRepository;

    public InventoryResponse addNewInventory(@RequestBody InventoryRequest inventoryRequest) {
        log.info("Adding a new inventory");
        Inventory inventory = inventoryRepository.save(mapToInventory(inventoryRequest));
        return mapToInventoryResponse(inventory);
    }


    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }


}
