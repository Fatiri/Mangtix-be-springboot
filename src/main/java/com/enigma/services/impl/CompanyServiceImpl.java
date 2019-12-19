package com.enigma.services.impl;

import com.enigma.constanta.CompanyConstant;
import com.enigma.constanta.MessageConstant;
import com.enigma.entity.Company;
import com.enigma.entity.CompanyUser;
import com.enigma.entity.User;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.CompanyRepository;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements com.enigma.services.CompanyService{

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserService userService;

    @Override
    public Company saveCompany(Company company) {
        for(CompanyUser companyUser:company.getCompanyUsers()){
            companyUser.setCompany(company);
            User user= userService.getUserById(companyUser.getUserIdTransient());
            companyUser.setUser(user);
        }
        return companyRepository.save(company);}

    @Override
    public Company getCompanyById(String id) {
        if(!companyRepository.findById(id).isPresent()){
            throw new NotFoundException(String.format(CompanyConstant.ID_COMPANY_NOT_FOUND, id));
        }
        return companyRepository.findById(id).get();
    }

    @Override
    public List<Company> getAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public void delete(String id) {
    companyRepository.deleteById(id);
    }
}
