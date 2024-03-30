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
        return warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Optional<Warehouse> getWarehouseById(Long id) throws Exception {
        return Optional.of(warehouseRepository.findById(id)
                .orElseThrow(() -> new Exception("Warehouse not found with ID: "  + id)));
    }

    @Override
    public Warehouse updateWarehouse(Warehouse warehouse) throws Exception {
       if(warehouseRepository.existsById(warehouse.getId())){
           return warehouseRepository.save(warehouse);
       }else {
           throw new Exception("Warehouse not found with ID: " + warehouse.getId());
       }
    }

    @Override
    public boolean deleteWarehouse(Long id) {
        if(warehouseRepository.existsById(id)){
            warehouseRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
