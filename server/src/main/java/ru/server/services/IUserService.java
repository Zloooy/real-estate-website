package ru.server.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.server.models.User;

import java.util.List;

public interface IUserService extends UserDetailsService
{
    User findById(Long id);
    List<User> getAll();
}
