package com.skillstorm.inventorymanagement.Controller;

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
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * ************************************
     * CREATE OPERATIONS
     * ************************************
     */
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product newProduct = productService.saveProduct(product);
        return ResponseEntity.ok(newProduct);
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
            product.setId(id);
            Product updatedProduct = productService.updateProduct(product);
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
