package com.enigma.repositories;

import com.enigma.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, String> {
    Role findRoleByRoleName(String roleName);
}
