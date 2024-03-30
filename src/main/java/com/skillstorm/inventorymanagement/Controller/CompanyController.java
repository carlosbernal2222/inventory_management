package com.skillstorm.inventorymanagement.Controller;

import com.skillstorm.inventorymanagement.Model.Administrator;
import com.skillstorm.inventorymanagement.Model.Company;
import com.skillstorm.inventorymanagement.Model.Product;
import com.skillstorm.inventorymanagement.Service.CompanyService;
import com.skillstorm.inventorymanagement.Service.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    /**
     * ************************************
     * CREATE OPERATIONS
     * ************************************
     */

    // Create a new company
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company newCompany = companyService.createCompany(company);
        return ResponseEntity.ok(company);
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
    public ResponseEntity<List<Company>> getAllCompanies(){
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }
    /*
    Find Company by id
     */

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) throws Exception {
        Optional<Company> company = companyService.getCompanyById(id);

        return company.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * ************************************
     * END OF READ OPERATIONS
     * ************************************
     */

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company companyDetails) {
        try {
            Company updatedCompany = companyService.updateCompany(id, companyDetails);
            return ResponseEntity.ok(updatedCompany);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping ({"/{id}"})
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id){
       boolean isDeleted = companyService.deleteCompany(id);
       if(isDeleted){
           return ResponseEntity.ok().build();
       }else{
           return ResponseEntity.notFound().build();
       }
    }


}
