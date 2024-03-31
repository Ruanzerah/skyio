package com.ruanzerah.skyio.domain.user;

import com.ruanzerah.skyio.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {}