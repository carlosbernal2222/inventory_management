package com.skillstorm.inventorymanagement.Service;

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
    public Company updateCompany(Long id, Company companyDetails) throws Exception {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new Exception("Company not found with ID: " + id));

        // Update company attributes explicitly, checking for null
        if (companyDetails.getName() != null) {
            company.setName(companyDetails.getName());
        }
        if (companyDetails.getAddress() != null) {
            company.setAddress(companyDetails.getAddress());
        }
        if (companyDetails.getPhoneNumber() != null) {
            company.setPhoneNumber(companyDetails.getPhoneNumber());
        }
        if (companyDetails.getIndustry() != null) {
            company.setIndustry(companyDetails.getIndustry());
        }
        if (companyDetails.getDescription() != null) {
            company.setDescription(companyDetails.getDescription());
        }

        return companyRepository.save(company);
    }


    @Override
    public boolean deleteCompany(Long id) {

        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }else {
            return false;
        }

    }
}
