package com.enigma.repositories;

import com.enigma.entity.CompanyUser;
import com.enigma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyUserRepository extends JpaRepository<CompanyUser, String> {
    CompanyUser findCompanyUserByUser(User user);
}
