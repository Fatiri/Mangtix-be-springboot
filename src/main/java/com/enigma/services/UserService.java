package com.enigma.services;

import com.enigma.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {

    public User saveUser(User user);
    public List<User> getAllUser();
    public User getUserById(String userId);
    public void deleteUserById(String userId);

}
