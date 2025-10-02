package com.wahiemwonderemporium.ordersms.utils;

import com.wahiemwonderemporium.ordersms.model.Order;
import com.wahiemwonderemporium.ordersms.model.OrderLineItems;
import com.wahiemwonderemporium.ordersms.model.viewModels.OrderLineItemsDto;
import com.wahiemwonderemporium.ordersms.model.viewModels.OrderRequest;
import com.wahiemwonderemporium.ordersms.model.viewModels.OrderResponse;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.UUID;

@UtilityClass
@Data
@Builder
public class Converters {

    public static Order mapToOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString()).build();

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream()
                .map(Converters::mapToOrderLineItems).toList();
        order.setOrderLineItemsList(orderLineItemsList);

        return order;
    }

    public static OrderResponse mapToOrderResponse(Order order){
        OrderResponse orderResponse = OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .build();
       List<OrderLineItemsDto>  orderLineItemsDtoList = order.getOrderLineItemsList()
               .stream()
               .map(Converters::mapToOrderLineItemsDto)
               .toList();

       orderResponse.setOrderLineItemsDtoList(orderLineItemsDtoList);

         return orderResponse;
    }

    public static OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto){
        return OrderLineItems.builder()
                .quantity(orderLineItemsDto.getQuantity())
                .price(orderLineItemsDto.getPrice())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }

    public static OrderLineItemsDto mapToOrderLineItemsDto(OrderLineItems orderLineItems){
        return  OrderLineItemsDto.builder()
                .skuCode(orderLineItems.getSkuCode())
                .quantity(orderLineItems.getQuantity())
                .price(orderLineItems.getPrice())
                .build();
    }
}
