package com.skillstorm.inventorymanagement.Controller;

import com.skillstorm.inventorymanagement.Model.Inventory;
import com.skillstorm.inventorymanagement.Service.InventoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory") // Base path for all endpoints in this controller
@CrossOrigin(origins = "http://localhost:5173")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService; // Assuming you have an interface InventoryService

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProductToWarehouse(@RequestParam Long productId, @RequestParam Long warehouseId, @RequestParam Integer quantity) {
        try {
            Inventory newInventory = inventoryService.addProductToWarehouse(productId, warehouseId, quantity);
            return ResponseEntity.ok(newInventory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<?> getInventoryByWarehouse(@PathVariable Long warehouseId) {
        try {
            var inventory = inventoryService.getInventoryByWarehouseId(warehouseId); // Implement this method in your service
            return ResponseEntity.ok(inventory);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/updateQuantity")
    public ResponseEntity<?> updateInventoryQuantity(@RequestParam Long warehouseId, @RequestParam Long productId, @RequestParam Integer newQuantity) {
        try {
            inventoryService.updateProductQuantityInWarehouse(productId, warehouseId, newQuantity); // Implement this method in your service
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/removeProduct")
    public ResponseEntity<?> removeProductFromWarehouse(@RequestParam Long warehouseId, @RequestParam Long productId) {
        try {
            inventoryService.removeProductFromWarehouse(productId, warehouseId); // Implement this method in your service
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
