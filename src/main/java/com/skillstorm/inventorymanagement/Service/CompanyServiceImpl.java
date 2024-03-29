package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.Model.Administrator;
import com.skillstorm.inventorymanagement.Model.Company;
import com.skillstorm.inventorymanagement.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> getCompanyById(Long id) throws Exception {

        return Optional.of(companyRepository.findById(id)
                .orElseThrow(() -> new Exception("Company not found with ID: " + id)));
    }

    @Override
    public Company updateCompany(Company company) throws Exception {
        //Logic to update company info Checks if the company exists before proceeding.
        if(companyRepository.existsById(company.getId())){
            return companyRepository.save(company);
        } else {
            throw new Exception("Company not found with ID: " + company.getId());
        }
    }

    @Override
    public void deleteCompany(Long id) {

        if(companyRepository.existsById(id)){
            companyRepository.deleteById(id);
        }

    }
}
