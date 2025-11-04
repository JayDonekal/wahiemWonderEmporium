package com.wahiemwonderemporium.productsms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {

    private String name;
    private String description;
    private Float price;
    private String skuCode;
}
