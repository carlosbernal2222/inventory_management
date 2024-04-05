package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.DTO.WarehouseCreationDto;
import com.skillstorm.inventorymanagement.Model.Category;
import com.skillstorm.inventorymanagement.Model.Company;
import com.skillstorm.inventorymanagement.Model.Warehouse;
import com.skillstorm.inventorymanagement.Repository.CategoryRepository;
import com.skillstorm.inventorymanagement.Repository.CompanyRepository;
import com.skillstorm.inventorymanagement.Repository.WarehouseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class WarehouseServiceImplTest {

    @Mock
    private WarehouseRepository warehouseRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private WarehouseServiceImpl warehouseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllWarehouses() {
        List<Warehouse> expectedWarehouses = Arrays.asList(new Warehouse(), new Warehouse());
        when(warehouseRepository.findAll()).thenReturn(expectedWarehouses);

        List<Warehouse> warehouses = warehouseService.getAllWarehouses();

        assertEquals(expectedWarehouses.size(), warehouses.size());
    }

    @Test
    public void testGetWarehouseById() throws Exception {
        Long warehouseId = 1L;
        Warehouse expectedWarehouse = new Warehouse(); // Populate this with expected data
        when(warehouseRepository.findById(warehouseId)).thenReturn(Optional.of(expectedWarehouse));

        Optional<Warehouse> warehouse = warehouseService.getWarehouseById(warehouseId);

        assertTrue(warehouse.isPresent());
        assertEquals(expectedWarehouse, warehouse.get());
    }

    @Test
    public void testDeleteWarehouse() {
        Long warehouseId = 1L;
        when(warehouseRepository.existsById(warehouseId)).thenReturn(true);

        boolean isDeleted = warehouseService.deleteWarehouse(warehouseId);

        assertTrue(isDeleted);
    }


}