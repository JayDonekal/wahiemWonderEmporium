package com.wahiemWonderEmporium.inventoryms.service;


import com.wahiemWonderEmporium.inventoryms.model.Inventory;
import com.wahiemWonderEmporium.inventoryms.model.viewModel.InventoryRequest;
import com.wahiemWonderEmporium.inventoryms.model.viewModel.InventoryResponse;
import com.wahiemWonderEmporium.inventoryms.repository.InventoryRepository;
import com.wahiemWonderEmporium.inventoryms.utils.Converters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static com.wahiemWonderEmporium.inventoryms.utils.Converters.mapToInventory;
import static com.wahiemWonderEmporium.inventoryms.utils.Converters.mapToInventoryResponse;


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

    public List<InventoryResponse> getAllInventory() {
        return inventoryRepository.findAll().stream()
                 .map(Converters::mapToInventoryResponse).toList();
    }


    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodeList) {

        List<InventoryResponse> inventoryResponseList =
                inventoryRepository.findBySkuCodeIn(skuCodeList).stream()
                .map(inventory ->{
                    InventoryResponse response = mapToInventoryResponse(inventory);
//                    response.setInStock(inventory.getQuantity() > 0);
                    return response;
                }).toList();

        return inventoryResponseList;
    }


}
