package com.wahiemwonderemporium.ordersms.controller;


import com.wahiemwonderemporium.ordersms.model.viewModels.OrderRequest;
import com.wahiemwonderemporium.ordersms.model.viewModels.OrderResponse;
import com.wahiemwonderemporium.ordersms.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    private ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<>(orderService.placeOrder(orderRequest), HttpStatus.CREATED);
    }

    @GetMapping
    private ResponseEntity<List<OrderResponse>> getAllOrders() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }
}
