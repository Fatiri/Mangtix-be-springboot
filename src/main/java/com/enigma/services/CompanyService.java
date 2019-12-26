package com.enigma.services;

import com.enigma.entity.Company;
import com.enigma.entity.CompanyUser;

import java.util.List;

public interface CompanyService {
    Company saveCompany(Company company);

    Company getCompanyById(String id);

    List<Company> getAllCompany();

    void delete(String id);

    CompanyUser getCompanyByUser(String id);
}
