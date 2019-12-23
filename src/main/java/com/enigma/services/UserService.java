package com.enigma.services;

import com.enigma.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    public User saveUser(User user);
    public List<User> getAllUser();
    public User getUserById(String userId);
    public void deleteUserById(String userId);
    public User findByUserName(String userName);

}
