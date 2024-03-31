package com.ruanzerah.skyio.controllers;

import com.ruanzerah.skyio.config.SecurityConfig;
import com.ruanzerah.skyio.entities.dtos.UserDto;
import com.ruanzerah.skyio.security.SecurityRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;
@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final SecurityRepository securityRepository;

    public LoginController(AuthenticationManager authenticationManager, SecurityRepository securityRepository) {
        this.authenticationManager = authenticationManager;
        this.securityRepository = securityRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserDto loginRequest) {
        // Create an authentication token with the provided username and password
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

        try {
            // Authenticate the user using the AuthenticationManager
            Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

            // If authentication is successful, set the security context
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authenticationResponse);

            // Obtain HttpServletRequest and HttpServletResponse
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest httpRequest = Objects.requireNonNull(attributes).getRequest();
            HttpServletResponse httpResponse = attributes.getResponse();

            // Save the security context
            securityRepository.saveContext(securityContext, httpRequest, httpResponse);

            // Return a successful response
            return ResponseEntity.ok().build();
        } catch (AuthenticationException e) {
            // If authentication fails, return an unauthorized response
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
