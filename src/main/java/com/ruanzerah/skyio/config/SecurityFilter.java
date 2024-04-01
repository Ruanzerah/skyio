package com.ruanzerah.skyio.config;

import com.ruanzerah.skyio.domain.user.User;
import com.ruanzerah.skyio.domain.user.UserRepository;
import com.ruanzerah.skyio.token.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@AllArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        tokenService.validate(token).ifPresent(log -> {
            User user = userRepository.findByEmail(log).orElseThrow();
            SimpleGrantedAuthority roleUser = new SimpleGrantedAuthority("ROLE_USER");
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(roleUser)));
        });
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (auth == null) return null;
        return auth.replace("Bearer ", "");
    }
}
