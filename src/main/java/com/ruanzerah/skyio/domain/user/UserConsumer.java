//package com.ruanzerah.skyio.domain.user;
//
//import com.ruanzerah.skyio.domain.token.TokenService;
//import lombok.AllArgsConstructor;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class UserConsumer {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final TokenService tokenService;
//
//    @KafkaListener(topics = "user_creation", groupId = "1")
//    public void receive(UserDTO dto) {
//        User user = new User(dto);
//        user.setPassword(passwordEncoder.encode(dto.password()));
//        userRepository.save(user);
//    }
//}
