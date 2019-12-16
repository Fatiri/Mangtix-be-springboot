package com.enigma.services.impl;

import com.enigma.constanta.StringConstant;
import com.enigma.entity.User;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.UserRepository;
import com.enigma.services.UserService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        if (!userRepository.findById(userId).isPresent()){
            throw new NotFoundException(String.format(StringConstant.ID_USER_NOT_FOUND, userId));
        }
        return userRepository.findById(userId).get();
    }

    @Override
    public void deleteUserById(String userId) {
        userRepository.deleteById(userId);
    }

}
