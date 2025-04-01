package com.arp.bg.pich_ai.service;

import com.arp.bg.pich_ai.domain.Login;
import com.arp.bg.pich_ai.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public List<Login> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public Optional<Login> findByUsername(String username) {
        return loginRepository.findById(username);
    }

    @Override
    public Login save(Login login) {
        return loginRepository.save(login);
    }

    @Override
    public void deleteByUsername(String username) {
        loginRepository.deleteById(username);
    }
}
