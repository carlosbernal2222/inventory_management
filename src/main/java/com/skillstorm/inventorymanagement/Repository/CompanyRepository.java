package com.skillstorm.inventorymanagement.Repository;

import com.skillstorm.inventorymanagement.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {



}
