package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.Model.Product;
import com.skillstorm.inventorymanagement.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveMultipleProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);

    }
}
