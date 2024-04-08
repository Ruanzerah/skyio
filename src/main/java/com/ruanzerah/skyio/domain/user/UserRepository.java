package com.ruanzerah.skyio.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
//    Flux<User> findAllBy(Pageable pageable);

    Optional<User> findByName(String name);
}