package com.wahiemwonderemporium.productsms.utils;

import com.wahiemwonderemporium.productsms.model.Product;
import com.wahiemwonderemporium.productsms.model.ProductRequest;
import com.wahiemwonderemporium.productsms.model.ProductResponse;
import lombok.Builder;
import lombok.Data;import lombok.experimental.UtilityClass;

@UtilityClass
@Data
@Builder
public class Converters {

    public static Product mapToProduct(ProductRequest productRequest){
        return Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
    }

    public static ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
