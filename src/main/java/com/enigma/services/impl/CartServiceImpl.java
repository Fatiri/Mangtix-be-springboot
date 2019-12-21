package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.entity.Cart;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.CartRepository;
import com.enigma.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public Page<Cart> getPageCart(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    @Override
    public Cart getCartById(String id) {
        if (!cartRepository.findById(id).isPresent()) {
            throw new NotFoundException(String.format(MessageConstant.ID_CART_NOT_FOUND));
        }
        return cartRepository.findById(id).get();
    }

    @Override
    public void deleteCartById(String id) {
        cartRepository.deleteById(id);
    }
}
