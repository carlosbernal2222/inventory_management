package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.Model.Inventory;
import com.skillstorm.inventorymanagement.Model.InventoryKey;

import java.util.List;

public interface InventoryService {

    Inventory addProductToWarehouse(Long productId, Long warehouseId, Integer quantity) throws Exception;
    List<Inventory> getInventoryByWarehouseId(Long warehouseId) throws Exception;
    void removeProductFromWarehouse(Long productId, Long warehouseId);

    void updateProductQuantityInWarehouse(Long productId, Long warehouseId, Integer newQuantity) throws Exception;
}
