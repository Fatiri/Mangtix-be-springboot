package com.enigma.services;

import com.enigma.entity.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {
    Cart saveCart(Cart cart);

    List<Cart> getAllCart();
    Page<Cart> getPageCart(Pageable pageable);
    Cart getCartById(String id);

    void deleteCartById(String id);
    List<Cart> getCartsByUser(String userId);
}
