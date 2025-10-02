package com.wahiemwonderemporium.ordersms.service;

import com.wahiemwonderemporium.ordersms.model.Order;
import com.wahiemwonderemporium.ordersms.model.viewModels.OrderRequest;
import com.wahiemwonderemporium.ordersms.model.viewModels.OrderResponse;
import com.wahiemwonderemporium.ordersms.repository.OrderRepository;
import com.wahiemwonderemporium.ordersms.utils.Converters;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static com.wahiemwonderemporium.ordersms.utils.Converters.mapToOrder;
import static com.wahiemwonderemporium.ordersms.utils.Converters.mapToOrderResponse;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Order Request Received");
        return mapToOrderResponse(orderRepository.save(mapToOrder(orderRequest)));
    }

    public List<OrderResponse> getOrders() {
        log.info("Getting all orders");
        List<Order> orders = orderRepository.findAll();
        List<OrderResponse> orderResponseList = orders.stream().map(Converters::mapToOrderResponse).toList();
        return orderResponseList;

    }
}
