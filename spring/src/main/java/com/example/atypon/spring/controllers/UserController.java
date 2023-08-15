package com.example.atypon.spring.controllers;

import com.example.atypon.spring.models.User;
import com.example.atypon.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> addUser(@RequestBody User user){
        try {
            userService.addUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to add user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Successfully added user", HttpStatus.OK);
    }

}
