package com.skillstorm.inventorymanagement.Repository;

import com.skillstorm.inventorymanagement.Model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

}
