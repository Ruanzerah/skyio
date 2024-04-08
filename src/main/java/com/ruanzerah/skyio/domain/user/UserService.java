package com.ruanzerah.skyio.domain.user;

import com.ruanzerah.skyio.domain.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public UserResponseDTO create(UserDTO dto) {
        User user = new User(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        userRepository.save(user);
        return new UserResponseDTO(user, tokenService.generate(user));
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public User getByID(UUID id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    //assync
//    public User getByID(UUID id) {
//        return userRepository.findById(id).blockOptional().orElseThrow(RuntimeException::new);
//    }

//    private final R2dbcEntityTemplate template;


//    public Page<User> getAll(Pageable pageable) {
//        return userRepository.findAllBy(pageable).collectList().zipWith(userRepository.count()).map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2())).block();
//    }


    //noaasssync

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
