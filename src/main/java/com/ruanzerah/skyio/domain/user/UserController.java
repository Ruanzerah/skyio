package com.ruanzerah.skyio.domain.user;

import com.ruanzerah.skyio.domain.email.Email;
import com.ruanzerah.skyio.domain.email.EmailProducer;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    private final EmailProducer emailProducer;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.getByID(id));
    }

    @PostMapping("/email")
    public ResponseEntity<Object> getUserByID(@RequestBody Email email) {
        emailProducer.send(email);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping
    public ResponseEntity<User> deleteAll() {
        userService.deleteAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "all", params = {"page", "size"})
    @Cacheable("requests")
    @CacheEvict(value = "requests", allEntries = true)
    public List<User> findPaginate(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userService.getAll(pageable).getContent();
    }
}
