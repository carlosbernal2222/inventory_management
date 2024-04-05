package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.Model.Inventory;
import com.skillstorm.inventorymanagement.Model.InventoryKey;
import com.skillstorm.inventorymanagement.Model.Product;
import com.skillstorm.inventorymanagement.Model.Warehouse;
import com.skillstorm.inventorymanagement.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private WarehouseService warehouseService;


    public List<Inventory> getInventoryByWarehouseId(Long warehouseId) throws Exception {
        Warehouse warehouse = warehouseService.getWarehouseById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        return inventoryRepository.findByWarehouse(warehouse);
    }


    @Override
    public Inventory addProductToWarehouse(Long productId, Long warehouseId, Integer quantity) throws Exception {

        Optional<Product> optionalProduct = productService.getProductById(productId);
        Optional<Warehouse> optionalWarehouse = warehouseService.getWarehouseById(warehouseId);

        Product product = optionalProduct.orElseThrow(()-> new RuntimeException("Optional Product is empty"));
        Warehouse warehouse = optionalWarehouse.orElseThrow(()-> new RuntimeException("Optional Warehouse is empty"));

        // Check if product and warehouse categories match
        if (!product.getCategory().equals(warehouse.getCategory())) {
            throw new Exception("Product category and warehouse category do not match.");
        }

        // Check if the product is refrigerated and if the warehouse has environment control
        if (product.isRefrigerated() && !warehouse.isEnvironmentControl()) {
            throw new Exception("Refrigerated products can only be added to warehouses with environmental control.");
        }

        // Calculate the total current inventory quantity
        int totalQuantity = warehouse.getInventory().stream()
                .mapToInt(Inventory::getQuantityAvailable)
                .sum();

        // Check if adding the new quantity exceeds the warehouse's capacity
        if (totalQuantity + quantity > warehouse.getCapacity()) {
            throw new Exception("Adding the specified quantity would exceed the warehouse's capacity.");
        }

        // Check for existing inventory record
        Optional<Inventory> existingInventory = inventoryRepository
                .findByWarehouseAndProduct(warehouse,product);
        if(existingInventory.isPresent()) {
            // If inventory exists, update its quantity
            Inventory inventory = existingInventory.get();
            inventory.setQuantityAvailable(inventory.getQuantityAvailable() + quantity);
            inventoryRepository.save(inventory);
            return inventory;
        }

        // If no existing inventory, create a new one
        Inventory newInventory = new Inventory();
        newInventory.setProduct(product);
        newInventory.setWarehouse(warehouse);
        newInventory.setQuantityAvailable(quantity);
        inventoryRepository.save(newInventory);


        return newInventory;
    }

    @Override
    public void updateProductQuantityInWarehouse(Long productId, Long warehouseId, Integer newQuantity) throws Exception {
        // Fetch the product and warehouse from the database
        Optional<Product> optionalProduct = productService.getProductById(productId);
        Optional<Warehouse> optionalWarehouse = warehouseService.getWarehouseById(warehouseId);

        Product product = optionalProduct.orElseThrow(()-> new RuntimeException("Optional Product is empty"));
        Warehouse warehouse = optionalWarehouse.orElseThrow(()-> new RuntimeException("Optional Warehouse is empty"));

        // Validate product and warehouse compatibility
        if (!product.getCategory().equals(warehouse.getCategory())) {
            throw new Exception("Product category and warehouse category do not match.");
        }

        if (product.isRefrigerated() && !warehouse.isEnvironmentControl()) {
            throw new Exception("Refrigerated products require a warehouse with environmental control.");
        }

        // Check for existing inventory record
        Optional<Inventory> inventoryOpt = inventoryRepository.findByWarehouseAndProduct(warehouse, product);
        if (!inventoryOpt.isPresent()) {
            throw new Exception("Product not found in the specified warehouse.");
        }

        Inventory inventory = inventoryOpt.get();

        // Calculate the total current inventory quantity excluding the current product
        int totalOtherProductsQuantity = warehouse.getInventory().stream()
                .filter(inv -> !inv.getProduct().equals(product))
                .mapToInt(Inventory::getQuantityAvailable)
                .sum();

        // Check if the new total quantity exceeds the warehouse's capacity
        if (totalOtherProductsQuantity + newQuantity > warehouse.getCapacity()) {
            throw new Exception("Updating the quantity would exceed the warehouse's capacity.");
        }

        // Update the inventory with the new quantity
        inventory.setQuantityAvailable(newQuantity);
        inventoryRepository.save(inventory);
    }




    @Override
    public void removeProductFromWarehouse(Long productId, Long warehouseId) {
        Inventory inventory = inventoryRepository.findByWarehouseIdAndProductId(warehouseId, productId)
                .orElseThrow(() -> new RuntimeException("Inventory entry not found"));

        if (inventory.getQuantityAvailable() > 0) {
            // Assuming business logic might require adjusting this condition,
            // like setting to a specific quantity or removing the entry altogether
            inventory.setQuantityAvailable(0); // Adjust based on requirement
            inventoryRepository.save(inventory);
        } else {
            // Optionally, if quantity is already 0, consider if you want to delete the record
            inventoryRepository.delete(inventory);
        }
    }

}
