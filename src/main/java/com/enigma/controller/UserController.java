package com.enigma.controller;

import com.enigma.entity.User;
import com.enigma.security.AuthenticationResponse;
import com.enigma.security.JwtUtil;
import com.enigma.security.UserPrincipalDetailsService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserPrincipalDetailsService userPrincipalDetailsService;
    @Autowired
    JwtUtil jwtUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody User user) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new Exception("GAGAL", e);
        }
        final UserDetails userDetails = userPrincipalDetailsService.loadUserByUsername(user.getUserName());
        final  String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PermitAll
    @PostMapping("/user")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable String userId){
        return userService.getUserById(userId);
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUserById(@PathVariable String userId){
        userService.deleteUserById(userId);
    }
}
