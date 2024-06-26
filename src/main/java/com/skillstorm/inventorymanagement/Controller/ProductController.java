package com.skillstorm.inventorymanagement.Controller;

import com.skillstorm.inventorymanagement.DTO.ProductCreationDto;
import com.skillstorm.inventorymanagement.Model.Administrator;
import com.skillstorm.inventorymanagement.Model.Product;
import com.skillstorm.inventorymanagement.Service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * ************************************
     * CREATE OPERATIONS
     * ************************************
     */
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody ProductCreationDto productCreationDto){
        Product newProduct = productService.saveProduct(productCreationDto);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }


    /**
     * ************************************
     * END OF CREATE OPERATIONS
     * ************************************
     */

    /**
     * ************************************
     * READ OPERATIONS
     * ************************************
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    /*
    Find Product by id
     */

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws Exception {
        Optional<Product> product = productService.getProductById(id);

        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * ************************************
     * END OF READ OPERATIONS
     * ************************************
     */

    /**
     * UPDATE OPERATIONS
     */
    //Update Admin information
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product ) {

        try{
            Product updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * ***********************************
     * END OF UPDATE OPERATIONS
     * ************************************
     */


    /**
     * ************************************
     * DELETE OPERATIONS
     * ************************************
     */

    //Delete admin by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * ************************************
     * END OF DELETE OPERATIONS
     * ************************************
     */
}
