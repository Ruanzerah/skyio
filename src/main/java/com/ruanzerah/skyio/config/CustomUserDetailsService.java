package com.ruanzerah.skyio.config;

import com.ruanzerah.skyio.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("Algum erro desconhecido aconteceu, não foi possivel encontrar o seu usuario"));
    }
}
