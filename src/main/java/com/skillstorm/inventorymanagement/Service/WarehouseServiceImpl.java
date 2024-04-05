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


    //Dependency Injection of the repo interfaces
    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Warehouse createWarehouse(WarehouseCreationDto dto) {

        // Retrieving and validating the existence of Company and Category entities
        // before creating a new Warehouse
        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        // Setting up a new Warehouse entity with details from DTO
        Warehouse warehouse = new Warehouse();

        warehouse.setName(dto.getName());
        warehouse.setAddress(dto.getAddress());
        warehouse.setCapacity(dto.getCapacity());
        warehouse.setEnvironmentControl(dto.isEnvironmentControl());
        warehouse.setCompany(company);
        warehouse.setCategory(category);

        // Saving the new Warehouse entity to the repository
        return warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Optional<Warehouse> getWarehouseById(Long id) throws Exception {

        // Attempting to retrieve a Warehouse by ID, throwing an exception if not found
        return Optional.of(warehouseRepository.findById(id)
                .orElseThrow(() -> new Exception("Warehouse not found with ID: "  + id)));
    }

    @Override
    public Warehouse updateWarehouse(Long id, WarehouseUpdateDto dto) throws Exception {

        // Fetch the existing warehouse you wish to update
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found for this id :: " + id));

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));

        // Updating the Warehouse entity with new details from DTO
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

        // Checks if a Warehouse with the specified ID exists, and if so, deletes it
        if(warehouseRepository.existsById(id)){
            warehouseRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
