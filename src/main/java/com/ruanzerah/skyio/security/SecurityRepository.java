package com.ruanzerah.skyio.security;

import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityRepository extends SecurityContextRepository {
}

//TODO attach to SecurityConfig (Bean)