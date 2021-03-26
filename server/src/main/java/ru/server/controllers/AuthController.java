package ru.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.server.data.Auth;
import ru.server.services.ITokenService;
import ru.server.services.IUserService;

@RestController
public class AuthController {
    @Autowired
    IUserService userService;
    @Autowired
    ITokenService tokenService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @PostMapping("/auth")
    public Auth.Response auth(@RequestBody Auth.Request request) {
        UserDetails u = userService.loadUserByUsername(request.getLogin());
        if (passwordEncoder.matches(request.getPassword(), u.getPassword())) {
            return new Auth.Response(tokenService.getToken(u.getUsername()));
        }
        return null;
    }
}
