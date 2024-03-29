package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.Model.Administrator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AdministratorService {

    //Create Operations
    Administrator registerAdmin(Administrator admin);

    //Read Operations
    List<Administrator> getAllAdmins();
    Optional<Administrator> getAdminById(Long id) throws Exception;

    //Update Operations
    Administrator updateAdmin(Administrator admin) throws Exception;

    //Delete Operations
    boolean deleteAdmin(Long id);

}
