package com.enigma.services;

import com.enigma.entity.Role;

import java.util.List;

public interface RoleService {

    public Role saveRole(Role role);
    public List<Role> getAllRole();
    public Role getRoleById(String roleId);
    public void deleteRoleById(String roleId);
    Role getRoleByRoleName(String roleName);

}
