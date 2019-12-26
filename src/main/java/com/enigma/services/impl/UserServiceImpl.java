package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.constanta.StringConstant;
import com.enigma.entity.Role;
import com.enigma.entity.User;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.UserRepository;
import com.enigma.services.RoleService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;

    @Override
    public User saveUser(User user) {
        Role role = roleService.getRoleById(user.getRoleIdTransient());
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        if (!userRepository.findById(userId).isPresent()){
            throw new NotFoundException(String.format(MessageConstant.ID_USER_NOT_FOUND, userId));
        }
        return userRepository.findById(userId).get();
    }

    @Override
    public void deleteUserById(String userId) {
        if (!userRepository.findById(userId).isPresent()){
            throw new NotFoundException(String.format(MessageConstant.ID_USER_NOT_FOUND, userId));
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User  findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

}
