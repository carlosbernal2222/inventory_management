package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.Model.Warehouse;
import com.skillstorm.inventorymanagement.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    WarehouseRepository warehouseRepository;

    @Override
    public Warehouse createWarehouse(Warehouse warehouse) {
        return null;
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return null;
    }

    @Override
    public Optional<Warehouse> getWarehouseById(Long id) {
        return Optional.empty();
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) {
        return null;
    }

    @Override
    public void deleteWarehouse(Long id) {

    }
}
