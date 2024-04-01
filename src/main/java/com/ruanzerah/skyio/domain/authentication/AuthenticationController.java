package com.ruanzerah.skyio.domain.authentication;

import com.ruanzerah.skyio.domain.user.UserDTO;
import com.ruanzerah.skyio.domain.user.UserResponseDTO;
import com.ruanzerah.skyio.domain.user.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserDTO dto) {
        UserResponseDTO userResponseDTO = userService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(userResponseDTO.user().getId());
        return ResponseEntity.created(uri).body(userResponseDTO);
    }
}
