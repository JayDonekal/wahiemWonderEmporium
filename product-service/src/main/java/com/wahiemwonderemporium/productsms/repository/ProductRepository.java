package com.wahiemwonderemporium.productsms.repository;

import com.wahiemwonderemporium.productsms.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
}
