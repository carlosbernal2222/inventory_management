package com.skillstorm.inventorymanagement.Service;


import com.skillstorm.inventorymanagement.Model.Administrator;
import com.skillstorm.inventorymanagement.Repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdministratorServiceImpl implements AdministratorService {



    @Autowired
    AdministratorRepository administratorRepository;

    //Create Administrator
    @Override
    public Administrator registerAdmin(Administrator admin) {
        return administratorRepository.save(admin);
    }

    @Override
    public List<Administrator> getAllAdmins() {
        return administratorRepository.findAll();
    }

    @Override
    public Optional<Administrator> getAdminById(Long id) throws Exception {

        return Optional.of(administratorRepository.findById(id)
                .orElseThrow(() -> new Exception("Administrator not found with ID: " + id)));
    }

    @Override
    public Administrator updateAdmin(Administrator admin) throws Exception {
        // Logic to update an admin. Checks if the admin exists before proceeding.
        if (administratorRepository.existsById(admin.getId())) {
            return administratorRepository.save(admin);
        } else {
            throw new Exception("Administrator not found with ID: " + admin.getId());
        }
    }



    @Override
    public boolean deleteAdmin(Long id) {
        if(administratorRepository.existsById(id)){
            administratorRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
