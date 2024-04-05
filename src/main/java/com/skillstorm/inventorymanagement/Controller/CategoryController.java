package com.skillstorm.inventorymanagement.Controller;

import com.skillstorm.inventorymanagement.Model.Administrator;
import com.skillstorm.inventorymanagement.Model.Category;
import com.skillstorm.inventorymanagement.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/categories")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    /**
     * ************************************
     * READ OPERATIONS
     * ************************************
     */
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    /*
    Find Admin by id
     */

//    @GetMapping("/{id}")
//    public ResponseEntity<Administrator> getAdminById(@PathVariable Long id) throws Exception {
//        Optional<Administrator> admin = administratorService.getAdminById(id);
//
//        return admin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }


    /**
     * ************************************
     * END OF READ OPERATIONS
     * ************************************
     */
}
