package com.ruanzerah.skyio.domain.user;

import lombok.Getter;

@Getter
public enum UserRole {

    FOUNDER("FOUNDER"), ADMIN("ADMIN"), USER("USER");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
