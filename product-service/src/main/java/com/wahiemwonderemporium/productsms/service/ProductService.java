package com.wahiemwonderemporium.productsms.service;

import com.wahiemwonderemporium.productsms.model.Product;
import com.wahiemwonderemporium.productsms.model.ProductRequest;
import com.wahiemwonderemporium.productsms.model.ProductResponse;
import com.wahiemwonderemporium.productsms.repository.ProductRepository;
import com.wahiemwonderemporium.productsms.utils.Converters;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wahiemwonderemporium.productsms.utils.Converters.mapToProduct;
import static com.wahiemwonderemporium.productsms.utils.Converters.mapToProductResponse;

@Service
@RequiredArgsConstructor
@Builder
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest){
        log.info("saving to database");
        Product product = productRepository.save(mapToProduct(productRequest));
        log.info("Product with id - {} is saved to database", product.getId());
        return mapToProductResponse(product);
    }

    public List<ProductResponse> getAllProducts(){
        log.info("Getting all of the products");
        List<Product> products = productRepository.findAll();
        //converting to productResponse by streaming the data
        List<ProductResponse> productResponses = products.stream().map(Converters::mapToProductResponse).toList();
        return productResponses;
    }





}
