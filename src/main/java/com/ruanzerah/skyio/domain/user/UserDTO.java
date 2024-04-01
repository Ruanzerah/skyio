package com.ruanzerah.skyio.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(@NotBlank String name, @NotBlank String password, @NotBlank String email) {
}