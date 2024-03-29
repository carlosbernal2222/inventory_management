package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id);
    Product saveProduct(Product product);
    List<Product> saveMultipleProducts(List<Product> products);
    void deleteProduct(Long id);

}
