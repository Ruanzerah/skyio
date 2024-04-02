package com.ruanzerah.skyio.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.getByID(id));
    }

    @GetMapping(value = "all", params = {"page", "size"})
    public List<User> findPaginate(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getAll(pageable).getContent();
    }
}
