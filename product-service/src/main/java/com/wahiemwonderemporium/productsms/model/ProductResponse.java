package com.wahiemwonderemporium.productsms.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private Float price;
    private String skuCode;

}
