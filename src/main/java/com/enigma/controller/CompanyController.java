package com.enigma.controller;

import com.enigma.entity.Company;
import com.enigma.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/company")
    public Company saveCompany(@RequestBody Company company){
        return companyService.saveCompany(company);
    }

    @GetMapping("/company/{id}")
    public Company getCompanyById(@PathVariable String id){
        return companyService.getCompanyById(id);
    }

    @GetMapping("/company")
    public List<Company> getAllCompany(){
        return companyService.getAllCompany();
    }

    @DeleteMapping("/company/{id}")
    public void deleteById(@PathVariable String id){
        companyService.delete(id);
    }
}
