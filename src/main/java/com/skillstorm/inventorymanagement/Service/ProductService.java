package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.DTO.ProductCreationDto;
import com.skillstorm.inventorymanagement.Model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    Product saveProduct(ProductCreationDto dto);
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long id) throws Exception;
    Product  updateProduct(Long id, Product product) throws Exception;
    List<Product> saveMultipleProducts(List<Product> products);
    boolean deleteProduct(Long id);

}
