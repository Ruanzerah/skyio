package com.ruanzerah.skyio.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user_tb")
public class User {
    @Column(name = "username")
    private String username;
    private String password;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
