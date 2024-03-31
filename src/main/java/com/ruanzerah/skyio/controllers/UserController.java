package com.ruanzerah.skyio.controllers;

import com.ruanzerah.skyio.entities.User;
import com.ruanzerah.skyio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User userBody){
        return userService.createUser(userBody);
    }

    @GetMapping
    public ResponseEntity<User> getUserByID(@RequestParam(name = "id") UUID userId){
        return ResponseEntity.ok(userService.getUserByID(userId).orElseThrow());
    }

}
