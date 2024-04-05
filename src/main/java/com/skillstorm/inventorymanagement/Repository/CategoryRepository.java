package com.skillstorm.inventorymanagement.Repository;

import com.skillstorm.inventorymanagement.Model.Category;

import com.skillstorm.inventorymanagement.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

}
