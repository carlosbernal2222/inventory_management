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
    public Optional<Product> getProductById(Long id) throws Exception {

        return Optional.of(productRepository.findById(id).orElseThrow());
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
    public Product updateProduct(Long id, Product productDetails) throws Exception{
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found with ID:" + id));

        if (productDetails.getCode() != null) {
            product.setCode(productDetails.getCode());
        }

        if (productDetails.getName() != null) {
            product.setName(productDetails.getName());
        }

        if (productDetails.getDescription() != null) {
            product.setDescription(productDetails.getDescription());
        }

        if (productDetails.getSubCategory() != null) {
            product.setSubCategory(productDetails.getSubCategory());
        }

        return productRepository.save(product);

    }

    @Override
    public boolean deleteProduct(Long id) {
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }else{
            return false;
        }

    }
}
