package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.DTO.WarehouseCreationDto;
import com.skillstorm.inventorymanagement.DTO.WarehouseUpdateDto;
import com.skillstorm.inventorymanagement.Model.Category;
import com.skillstorm.inventorymanagement.Model.Company;
import com.skillstorm.inventorymanagement.Model.Warehouse;
import com.skillstorm.inventorymanagement.Repository.CategoryRepository;
import com.skillstorm.inventorymanagement.Repository.CompanyRepository;
import com.skillstorm.inventorymanagement.Repository.WarehouseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService{

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Warehouse createWarehouse(WarehouseCreationDto dto) {

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        Warehouse warehouse = new Warehouse();

        warehouse.setName(dto.getName());
        warehouse.setAddress(dto.getAddress());
        warehouse.setCapacity(dto.getCapacity());
        warehouse.setEnvironmentControl(dto.isEnvironmentControl());
        warehouse.setCompany(company);
        warehouse.setCategory(category);

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
    public Warehouse updateWarehouse(Long id, WarehouseUpdateDto dto) throws Exception {

        // First, fetch the existing warehouse you wish to update
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found for this id :: " + id));

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));



        warehouse.setName(dto.getName());
        warehouse.setAddress(dto.getAddress());
        warehouse.setCapacity(dto.getCapacity());
        warehouse.setEnvironmentControl(dto.isEnvironmentControl());
        warehouse.setCompany(company);
        warehouse.setCategory(category);

        Warehouse updatedWarehouse = warehouseRepository.save(warehouse);

        return updatedWarehouse;
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
