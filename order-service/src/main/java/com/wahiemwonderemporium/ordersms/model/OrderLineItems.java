package com.wahiemwonderemporium.ordersms.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="t_order_line_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String skuCode;
    private float price;
    private Integer quantity;
}
