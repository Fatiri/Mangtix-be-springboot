package com.enigma.services.impl;

import com.enigma.entity.Role;
import com.enigma.repositories.RoleRepository;
import com.enigma.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(String roleId) {
        return roleRepository.findById(roleId).get();
    }

    @Override
    public void deleteRoleById(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
