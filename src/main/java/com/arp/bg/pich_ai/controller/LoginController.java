package com.arp.bg.pich_ai.controller;

import com.arp.bg.pich_ai.domain.Login;
import com.arp.bg.pich_ai.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public List<Login> getAllLogins() {
        return loginService.findAll();
    }

    @GetMapping("/{username}")
    public ResponseEntity<Login> getLoginByUsername(@PathVariable String username) {
        return loginService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Login createLogin(@RequestBody Login login) {
        return loginService.save(login);
    }

    @PutMapping("/{username}")
    public ResponseEntity<Login> updateLogin(@PathVariable String username, @RequestBody Login login) {
        return loginService.findByUsername(username)
                .map(existingLogin -> {
                    login.setUsername(username);
                    return ResponseEntity.ok(loginService.save(login));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteLogin(@PathVariable String username) {
        return loginService.findByUsername(username)
                .map(login -> {
                    loginService.deleteByUsername(username);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/login-page")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/authenticate")
    public String authenticateUser(@RequestParam String username, @RequestParam String password) {
        Optional<Login> login = loginService.findByUsername(username);
        if (login.isPresent() && login.get().getPassword().equals(password)) {
            return "redirect:/dashboard";
        }
        return "redirect:/api/login/login-page?error";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("login", new Login());
        return "register";
    }
}
