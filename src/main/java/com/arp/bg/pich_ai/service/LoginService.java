package com.arp.bg.pich_ai.service;

import com.arp.bg.pich_ai.domain.Login;
import java.util.List;
import java.util.Optional;

public interface LoginService {
    List<Login> findAll();
    Optional<Login> findByUsername(String username);
    Login save(Login login);
    void deleteByUsername(String username);
}
