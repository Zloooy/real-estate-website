package ru.server.services;

import org.springframework.stereotype.Service;
import ru.server.data.UserRoleDto;

import java.util.List;

@Service
public interface IRoleService {
    List<UserRoleDto> getAllRoles();
}
