package com.skillstorm.inventorymanagement.Repository;

import com.skillstorm.inventorymanagement.Model.Inventory;
import com.skillstorm.inventorymanagement.Model.Product;
import com.skillstorm.inventorymanagement.Model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    //Finds and inventory record by its associated product and warehouse
    Optional<Inventory> findByWarehouseAndProduct(Warehouse warehouse,Product product);

    List<Inventory> findByWarehouse(Warehouse warehouse);

    Optional<Inventory> findByWarehouseIdAndProductId(Long warehouseId, Long productId);
}
