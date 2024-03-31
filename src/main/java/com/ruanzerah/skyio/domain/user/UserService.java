package com.ruanzerah.skyio.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User create(UserDTO dto) {
        User user = new User(dto);
        userRepository.save(user);
        return user;
    }

    public User getByID(UUID id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }
}
