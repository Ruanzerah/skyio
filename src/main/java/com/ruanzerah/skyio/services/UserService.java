package com.ruanzerah.skyio.services;

import com.ruanzerah.skyio.entities.User;
import com.ruanzerah.skyio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<User> createUser(User user){
        var newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }

    public Optional<User> getUserByID(UUID id){
        return userRepository.findById(id);
    }

}
