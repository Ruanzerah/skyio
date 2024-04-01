package com.ruanzerah.skyio.domain.user;

import com.ruanzerah.skyio.entities.dtos.UserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Table(name = "user_tb")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;

    public User(UserDTO dto) {
        this.name = dto.name();
        this.password = dto.password();
        this.email = dto.email();
    }

    public User(UserDto userDto){
        this.username = userDto.username();
        this.password = userDto.password();
    }
}
