package com.skillstorm.inventorymanagement.Controller;

import com.skillstorm.inventorymanagement.Model.Warehouse;
import com.skillstorm.inventorymanagement.Service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse){
        Warehouse newWarehouse = warehouseService.createWarehouse(warehouse);
        return ResponseEntity.ok(newWarehouse);
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> getAllWarehouses(){
        List<Warehouse> warehouses = warehouseService.getAllWarehouses();
        return ResponseEntity.ok(warehouses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Long id) throws Exception {
        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(id);

        return warehouse.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouse){

        try{
            warehouse.setId(id);
            Warehouse updatedWarehouse = warehouseService.updateWarehouse(warehouse);
            return ResponseEntity.ok(updatedWarehouse);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable Long id){
        boolean isDeleted = warehouseService.deleteWarehouse(id);
        if(isDeleted){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }


}
