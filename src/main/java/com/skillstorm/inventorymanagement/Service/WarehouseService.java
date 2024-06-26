package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.DTO.WarehouseCreationDto;
import com.skillstorm.inventorymanagement.DTO.WarehouseUpdateDto;
import com.skillstorm.inventorymanagement.Model.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseService {

    // Create operation
    Warehouse createWarehouse(WarehouseCreationDto dto);

    // Read operations
    List<Warehouse> getAllWarehouses();
    Optional<Warehouse> getWarehouseById(Long id) throws Exception;


    // Update operation
    Warehouse updateWarehouse(Long id, WarehouseUpdateDto dto) throws Exception;


    // Delete operation
    boolean deleteWarehouse(Long id);

}
