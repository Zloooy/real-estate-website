package ru.server.services;

import org.springframework.stereotype.Service;

@Service
public interface ITokenService {
    String getToken(String login);
    boolean validateToken(String token);
    String getLoginFromToken(String token);
}
