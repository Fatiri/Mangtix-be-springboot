package com.enigma.repositories;

import com.enigma.entity.Cart;
import com.enigma.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
    List<Cart> findCartsByUser(User user);
}
