package ru.server.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.server.data.UserDto;
import ru.server.models.User;

import java.util.List;

@Service
public interface IUserService extends UserDetailsService
{
    User findById(Long id);
    List<UserDto> getAll();
    boolean save(UserDto userDto);
}
