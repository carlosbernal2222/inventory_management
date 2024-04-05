package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.DTO.ProductCreationDto;
import com.skillstorm.inventorymanagement.Model.Category;
import com.skillstorm.inventorymanagement.Model.Product;
import com.skillstorm.inventorymanagement.Repository.CategoryRepository;
import com.skillstorm.inventorymanagement.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    // Injecting dependencies for repositories to interact with the database
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Product> getAllProducts() {
        // Returns a list of all products in the database
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) throws Exception {
        // Retrieves a product by its ID, or throws an exception if not found
        return Optional.of(productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id)));
    }

    @Override
    public Product saveProduct(ProductCreationDto dto) {
        // Finds the category by ID, throws an exception if not found
        // Creates and saves a new product based on the provided DTO
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Product product = new Product();
        // Setting product properties from the DTO
        product.setCode(dto.getCode());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setSubCategory(dto.getSubCategory());
        product.setRefrigerated(dto.isRefrigerated());
        product.setWeight(dto.getWeight());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public List<Product> saveMultipleProducts(List<Product> products) {
        // Saves multiple product entities at once
        return productRepository.saveAll(products);
    }

    @Override
    public Product updateProduct(Long id, Product productDetails) throws Exception{
        // Updates an existing product with new details, only if the product exists
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found with ID: " + id));

        // Conditional updates for product fields if they are not null
        if (productDetails.getCode() != null) {
            existingProduct.setCode(productDetails.getCode());
        }
        if (productDetails.getName() != null) {
            existingProduct.setName(productDetails.getName());
        }
        if (productDetails.getDescription() != null) {
            existingProduct.setDescription(productDetails.getDescription());
        }
        if (productDetails.getSubCategory() != null) {
            existingProduct.setSubCategory(productDetails.getSubCategory());
        }
        // Saves the updated product details to the database
        return productRepository.save(existingProduct);
    }

    @Override
    public boolean deleteProduct(Long id) {
        // Checks if a product exists before attempting to delete it
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
