package com.ruanzerah.skyio.domain.authentication;

import com.ruanzerah.skyio.domain.token.TokenService;
import com.ruanzerah.skyio.domain.user.User;
import com.ruanzerah.skyio.domain.user.UserDTO;
import com.ruanzerah.skyio.domain.user.UserResponseDTO;
import com.ruanzerah.skyio.domain.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserDTO dto) {
        UserResponseDTO userResponseDTO = userService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(userResponseDTO.user().getId());
        return ResponseEntity.created(uri).body(userResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserDTO dto) {
        Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.name(), dto.password()));
        return ResponseEntity.ok(tokenService.generate((User) authenticate.getPrincipal()));
    }
}
