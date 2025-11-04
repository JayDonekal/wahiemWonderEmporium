package com.wahiemwonderemporium.ordersms.service;

import com.wahiemwonderemporium.ordersms.model.Order;
import com.wahiemwonderemporium.ordersms.model.OrderLineItems;
import com.wahiemwonderemporium.ordersms.model.viewModels.InventoryResponse;
import com.wahiemwonderemporium.ordersms.model.viewModels.OrderRequest;
import com.wahiemwonderemporium.ordersms.model.viewModels.OrderResponse;
import com.wahiemwonderemporium.ordersms.repository.OrderRepository;
import com.wahiemwonderemporium.ordersms.utils.Converters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import static com.wahiemwonderemporium.ordersms.utils.Converters.mapToOrder;
import static com.wahiemwonderemporium.ordersms.utils.Converters.mapToOrderResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    @Value("${service.url.inventory}")
    private String inventoryUrl;

    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Order Request Received");

        Order order = mapToOrder(orderRequest);

        //Getting list of skuCode to make sure all line-items in the requested order is in inventory stock
        List<String> skuCodesList = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        log.info("Calling inventory service...");
        InventoryResponse[] inventoryResponseList;
        try {
            // call inventory-service and check if skuCodesList items are in stock before saving
            inventoryResponseList = webClient.get()
                    .uri(inventoryUrl + "/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodesList).build())
                    .retrieve()
                    .bodyToMono(InventoryResponse[].class)
                    .block();
        }
        catch (Exception e) {
            log.error("Error while calling inventory service.");
            throw new RuntimeException("Error while calling inventory service.", e);
        }
      //temporarily making this false until inventory response changes to true if all is in stock
        boolean allProductsInStock = false;
        if(inventoryResponseList.length > 0) {
            allProductsInStock = Arrays.stream(inventoryResponseList).allMatch(InventoryResponse::isInStock);
        }

        if(allProductsInStock){
           orderRepository.save(order);
        }
        else{
            throw new IllegalArgumentException("Product not available");
        }

        return mapToOrderResponse(order);
    }

    public List<OrderResponse> getOrders() {
        log.info("Getting all orders");
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(Converters::mapToOrderResponse).toList();

    }
}
