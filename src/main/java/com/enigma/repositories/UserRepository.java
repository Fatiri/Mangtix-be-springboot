package com.enigma.repositories;

import com.enigma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, String> {
    User findByUserName(String userName);
}
