package com.skillstorm.inventorymanagement.Service;


import com.skillstorm.inventorymanagement.Model.Administrator;
import com.skillstorm.inventorymanagement.Model.Company;
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
    public Administrator updateAdmin(Long id, Administrator adminDetails) throws Exception {
        // Logic to update an admin. Checks if the admin exists before proceeding.
        Administrator admin = administratorRepository.findById(id)
                .orElseThrow(() -> new Exception("Administrator not found with ID: " + id));

        if (adminDetails.getFirstName() != null) {
            admin.setFirstName(adminDetails.getFirstName());
        }
        if (adminDetails.getLastName() != null) {
            admin.setLastName(adminDetails.getLastName());
        }
        if(adminDetails.getAddress() != null){
            admin.setAddress(adminDetails.getAddress());
        }
        if (adminDetails.getEmail() != null){
            admin.setEmail(adminDetails.getEmail());
        }
        if (adminDetails.getUsername() != null){
            admin.setUsername(adminDetails.getUsername());
        }
        if (adminDetails.getPassword() != null){
            admin.setPassword(adminDetails.getPassword());
        }


        // Add other fields as necessary

        return administratorRepository.save(admin);
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
