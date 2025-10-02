package com.wahiemwonderemporium.productsms.controllers;

import com.wahiemwonderemporium.productsms.ProductsMsApplication;
import com.wahiemwonderemporium.productsms.model.Product;
import com.wahiemwonderemporium.productsms.model.ProductRequest;
import com.wahiemwonderemporium.productsms.model.ProductResponse;
import com.wahiemwonderemporium.productsms.repository.ProductRepository;
import com.wahiemwonderemporium.productsms.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.createProduct(productRequest);
        return productResponse;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
