package com.enigma.controller;

import com.enigma.entity.Role;
import com.enigma.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Roleontroller {

    @Autowired
    RoleService roleService;

    @PostMapping("/role")
    public Role saveRole(@RequestBody Role role){
        return roleService.saveRole(role);
    }

    @GetMapping("/role/{roleId}")
    public Role getRoleById(@PathVariable String roleId){
        return roleService.getRoleById(roleId);
    }

    @GetMapping("/roles")
    public List<Role> getAllRole(){
        return roleService.getAllRole();
    }

    @DeleteMapping("/role/{roleId}")
    public void deleteRoleById(@PathVariable String roleId){
        roleService.deleteRoleById(roleId);
    }

}
