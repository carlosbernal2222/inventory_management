package com.skillstorm.inventorymanagement.Repository;

import com.skillstorm.inventorymanagement.Model.Company;
import com.skillstorm.inventorymanagement.Model.Inventory;
import com.skillstorm.inventorymanagement.Model.Product;
import com.skillstorm.inventorymanagement.Model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByName(String name);

}
