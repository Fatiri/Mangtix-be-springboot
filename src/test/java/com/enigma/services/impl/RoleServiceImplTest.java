package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
import com.enigma.entity.Category;
import com.enigma.entity.Event;
import com.enigma.entity.Role;
import com.enigma.entity.Ticket;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.RoleRepository;
import com.enigma.repositories.TicketRepository;
import com.enigma.services.RoleService;
import com.enigma.services.TicketService;
import org.assertj.core.internal.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoleServiceImplTest {

    @Autowired
    RoleService roleService;
    @MockBean
    RoleRepository roleRepository;

    @Test
    void saveRole_should_callRoleRepository_save_once() {
        Integer mockitoTimes = 1;
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        Mockito.verify(roleRepository, Mockito.times(mockitoTimes)).save(role);

    }

    @Test
    void getAllRole_should_callRoleRepository_findAll_once() {
        Integer mockitoTimes = 1;
        String roleId="611b2dde-adef-4e92-ace0-4fc6231abeb6";
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        roleService.getAllRole();
        Mockito.verify(roleRepository, Mockito.times(mockitoTimes)).findAll();
    }

    @Test
    void getRoleById_should_callRoleRepository_findById_once() {
        Integer mockitoTimes = 2;
        String roleId = "611b2dde-adef-4e92-ace0-4fc6231abeb6";
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        roleService.getRoleById(roleId);
        Mockito.verify(roleRepository, Mockito.times(mockitoTimes)).findById(roleId);
    }

    @Test
    void getRoleById_should_returnThrow_when_role_notExist() {
        String dummyRoleId = "611b2dde-adef-4e92-ace0-4fc6231abeb6";
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        String notFound = String.format(MessageConstant.ID_Role_NOT_FOUND, dummyRoleId);
        try{
            roleService.getRoleById(dummyRoleId);
        }catch (Exception e){
            String actualError = e.getLocalizedMessage();
            assertEquals(notFound,actualError);
        }

    }

    @Test
    void deleteRoleById_should_findById_once() {
        Integer mockitoTimes = 1;
        String roleId = "611b2dde-adef-4e92-ace0-4fc6231abeb6";
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        roleService.deleteRoleById(roleId);
        Mockito.verify(roleRepository,Mockito.times(mockitoTimes)).findById(roleId);
    }

    @Test
    void deleteRoleById_should_returnThrow_when_role_notExist() {
        String dummyRoleId = "611b2dde-adef-4e92-ace0-4fc6231abeb6";
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        String notFound = String.format(MessageConstant.ID_Role_NOT_FOUND, dummyRoleId);
        try{
            roleService.deleteRoleById(dummyRoleId);
        }catch (Exception e){
            String actualError = e.getLocalizedMessage();
            assertEquals(notFound,actualError);
        }
    }

    @Test
    void deleteRoleById_should_callRoleRepository_deleteById_once() {
        Integer mockitoTimes = 1;
        String roleId = "611b2dde-adef-4e92-ace0-4fc6231abeb6";
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        Mockito.when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));
        roleService.deleteRoleById(roleId);
        Mockito.verify(roleRepository,Mockito.times(mockitoTimes)).deleteById(roleId);
    }
}