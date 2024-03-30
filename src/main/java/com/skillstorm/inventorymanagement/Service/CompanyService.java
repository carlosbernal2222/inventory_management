package com.skillstorm.inventorymanagement.Service;

import com.skillstorm.inventorymanagement.Model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company createCompany(Company company);
    List<Company> getAllCompanies();
    Optional<Company> getCompanyById(Long id) throws Exception;
    Company updateCompany(Long id, Company companyDetails) throws Exception;
    boolean deleteCompany(Long id);

}
