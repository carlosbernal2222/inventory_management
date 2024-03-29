package com.skillstorm.inventorymanagement.Controller;


import com.skillstorm.inventorymanagement.Model.Administrator;
import com.skillstorm.inventorymanagement.Service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admins")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    /**
     * ************************************
     * CREATE OPERATIONS
     * ************************************
     */

    // Create a new administrator
    @PostMapping
    public ResponseEntity<Administrator> registerAdmin(@RequestBody Administrator admin) {
        Administrator newAdmin = administratorService.registerAdmin(admin);
        return ResponseEntity.ok(newAdmin);
    }

    /**
     * ************************************
     * END OF CREATE OPERATIONS
     * ************************************
     */

    /**
     * ************************************
     * READ OPERATIONS
     * ************************************
     */
    @GetMapping
    public ResponseEntity<List<Administrator>> getAllAdmins(){
        List<Administrator> admins = administratorService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }
    /*
    Find Admin by id
     */

    @GetMapping("/{id}")
    public ResponseEntity<Administrator> getAdminById(@PathVariable Long id) throws Exception {
        Optional<Administrator> admin = administratorService.getAdminById(id);

        return admin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * ************************************
     * END OF READ OPERATIONS
     * ************************************
     */

    /**
     * UPDATE OPERATIONS
     */
    //Update Admin information
    @PutMapping("/{id}")
    public ResponseEntity<Administrator> updateAdmin(@PathVariable Long id, @RequestBody Administrator admin ) {

        try{
            admin.setId(id);
            Administrator updatedAdmin = administratorService.updateAdmin(admin);
            return ResponseEntity.ok(updatedAdmin);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /**
     * ***********************************
     * END OF UPDATE OPERATIONS
     * ************************************
     */


    /**
     * ************************************
     * DELETE OPERATIONS
     * ************************************
     */

    //Delete admin by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable Long id) {
        boolean isDeleted = administratorService.deleteAdmin(id);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * ************************************
     * END OF DELETE OPERATIONS
     * ************************************
     */

}//ENDOF CONTROLLER
