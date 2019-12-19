package com.enigma.services;

import com.enigma.entity.Company;

import java.util.List;

public interface CompanyService {
    Company saveCompany(Company company);

    Company getCompanyById(String id);

    List<Company> getAllCompany();

    void delete(String id);
}
