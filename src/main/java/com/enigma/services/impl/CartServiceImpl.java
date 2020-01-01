package com.enigma.services.impl;

import com.enigma.constanta.MessageConstant;
import com.enigma.entity.Cart;
import com.enigma.entity.Ticket;
import com.enigma.entity.User;
import com.enigma.exception.NotFoundException;
import com.enigma.repositories.CartRepository;
import com.enigma.services.CartService;
import com.enigma.services.TicketService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserService userService;
    @Autowired
    TicketService ticketService;
    @Override
    public Cart saveCart(Cart cart) {
        User user = userService.getUserById(cart.getUserIdTransient());
        cart.setUser(user);
        Ticket ticket= ticketService.getTicketById(cart.getTicketIdTransient());
        cart.setTicket(ticket);
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

    @Override
    public List<Cart> getCartsByUser(String userId){
        User user = userService.getUserById(userId);
        return cartRepository.findCartsByUser(user);
    }
}
