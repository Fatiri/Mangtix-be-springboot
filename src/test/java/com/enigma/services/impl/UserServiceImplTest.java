package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.entity.Role;
import com.enigma.entity.User;
import com.enigma.repositories.UserRepository;
import com.enigma.services.RoleService;
import com.enigma.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;
    @MockBean
    RoleService roleService;

    @Test
    void saveUser_should_callRoleService_getRoleById_once() {
        Integer mockitoTimes = 1;
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        String userName = "mangTik";
        String password = "mangTik";
        String fullName = "mangTik";
        String bornPlace = "Jakarta Barat";
        Date birthDate = new Date();
        Date createAt = new Date();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBornPlace(bornPlace);
        user.setBirthDate(birthDate);
        user.setCreateAt(createAt);
        user.setRoleIdTransient(role.getId());
        userService.saveUser(user);
        Mockito.verify(roleService, Mockito.times(mockitoTimes)).getRoleById(user.getRoleIdTransient());
    }

    @Test
    void saveUser_should_callUserRepository_setRole_once() {
        Integer mockitoTimes = 1;
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        String userName = "mangTik";
        String password = "mangTik";
        String fullName = "mangTik";
        String bornPlace = "Jakarta Barat";
        Date birthDate = new Date();
        Date createAt = new Date();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBornPlace(bornPlace);
        user.setBirthDate(birthDate);
        user.setCreateAt(createAt);
        user.setRoleIdTransient(role.getId());
        userService.saveUser(user);
        Mockito.verify(userRepository, Mockito.times(mockitoTimes)).save(user);
    }

    @Test
    void getAllUser() {
        Integer mockitoTimes = 1;
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        String userName = "mangTik";
        String password = "mangTik";
        String fullName = "mangTik";
        String bornPlace = "Jakarta Barat";
        Date birthDate = new Date();
        Date createAt = new Date();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBornPlace(bornPlace);
        user.setBirthDate(birthDate);
        user.setCreateAt(createAt);
        user.setRoleIdTransient(role.getId());
        userService.saveUser(user);
        userService.getAllUser();
        Mockito.verify(userRepository, Mockito.times(mockitoTimes)).findAll();
    }

    @Test
    void getUserById_should_callUserRepository_findById_twoTimes() {
        Integer mockitoTimes = 2;
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        String userID = "ca614b34-11c0-467a-b5ea-5901ab452c12";
        String userName = "mangTik";
        String password = "mangTik";
        String fullName = "mangTik";
        String bornPlace = "Jakarta Barat";
        Date birthDate = new Date();
        Date createAt = new Date();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBornPlace(bornPlace);
        user.setBirthDate(birthDate);
        user.setCreateAt(createAt);
        user.setRoleIdTransient(role.getId());
        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.of(user));
        userService.getUserById(userID);
        Mockito.verify(userRepository, Mockito.times(mockitoTimes)).findById(userID);
    }

    @Test
    void getUserById_should_returnThrow_when_userId_notExist() {
        String roleName = "EO";
        String description = "EO is organisation create data to application for give event for make some concert";
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        roleService.saveRole(role);
        String dummyRoleId = "ca614b34-11c0-467a-b5ea-5901ab452c12";
        String userName = "mangTik";
        String password = "mangTik";
        String fullName = "mangTik";
        String bornPlace = "Jakarta Barat";
        Date birthDate = new Date();
        Date createAt = new Date();
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setFullName(fullName);
        user.setBornPlace(bornPlace);
        user.setBirthDate(birthDate);
        user.setCreateAt(createAt);
        user.setRoleIdTransient(role.getId());
        userService.saveUser(user);
        String notFound = String.format(MessageConstant.ID_USER_NOT_FOUND, dummyRoleId);
       try {
           userService.getUserById(dummyRoleId);
       }catch (Exception e){
           String actualError = e.getLocalizedMessage();
           assertEquals(notFound, actualError);
       }
    }

    @Test
    void deleteUserById() {
    }
}