package com.enigma.controller;

import com.enigma.entity.Company;
import com.enigma.entity.CompanyUser;
import com.enigma.entity.User;
import com.enigma.security.AuthenticationResponse;
import com.enigma.security.JwtUtil;
import com.enigma.security.UserPrincipalDetailsService;
import com.enigma.services.CompanyService;
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
    CompanyService companyService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserPrincipalDetailsService userPrincipalDetailsService;
    @Autowired
    JwtUtil jwtUtil;
    @CrossOrigin
    @PermitAll
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
        User userEntity = userService.findByUserName(userDetails.getUsername());
        userEntity.getId();
        CompanyUser companyUser = companyService.getCompanyByUser(userEntity.getId());
        String companyId = "";
        if (companyUser==null){
            companyId = "";
        }else {
            Company company = companyService.getCompanyById(companyUser.getCompany().getId());
            companyId =company.getId();
        }
            System.out.println(companyUser);
        final  String jwt = jwtUtil.generateToken(userEntity.getId(), userEntity.getRole().getRoleName(), companyId);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    @CrossOrigin
    @PermitAll
    @PostMapping("/user")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @CrossOrigin
    @PermitAll
    @GetMapping("/user")
    public User getUserById(@RequestBody String userId){
        return userService.getUserById(userId);
    }
    @CrossOrigin
    @RolesAllowed("ADMIN")
    @GetMapping("/users")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }
    @RolesAllowed("ADMIN")
    @DeleteMapping("/user/{userId}")
    public void deleteUserById(@PathVariable String userId){
        userService.deleteUserById(userId);
    }
}
