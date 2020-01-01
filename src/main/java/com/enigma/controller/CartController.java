package com.enigma.controller;

import com.enigma.entity.Cart;
import com.enigma.services.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ObjectMapper objectMapper;

    @CrossOrigin
    @GetMapping("/carts")
    public List<Cart> getListCart(){
        return cartService.getAllCart();
    }

    @CrossOrigin
    @GetMapping("/cart/{id}")
    public Cart getcartByIdCart(@PathVariable String id){
        return cartService.getCartById(id);
    }

    @CrossOrigin
    @PostMapping("/cart")
    public Cart saveCart(@RequestBody Cart cart){
        return cartService.saveCart(cart);
    }
    @CrossOrigin
    @DeleteMapping("/cart/{id}")
    public void deleteCartById(@PathVariable String id){
        cartService.deleteCartById(id);
    }

    @CrossOrigin
    @GetMapping("/cart-page")
    public Page<Cart> getCartByPage(@RequestParam Integer size, @RequestParam Integer page){
        Pageable pageable = PageRequest.of(page, size);
        return cartService.getPageCart(pageable);
    }

    @CrossOrigin
    @GetMapping("/cart-user")
    public List<Cart> getCartByUserId(@RequestParam String userId){
        return cartService.getCartsByUser(userId);
    }
}
