package com.ruanzerah.skyio.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.create(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(user.getId());
        return ResponseEntity.created(uri).body(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.getByID(id));
    }

    @GetMapping("all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }
}
