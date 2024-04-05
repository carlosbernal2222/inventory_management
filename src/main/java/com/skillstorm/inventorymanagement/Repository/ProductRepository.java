package com.skillstorm.inventorymanagement.Repository;

import com.skillstorm.inventorymanagement.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
