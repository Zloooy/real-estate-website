package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.server.data.UserRoleDto;
import ru.server.repositories.IRoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<UserRoleDto> getAllRoles() {
        return roleRepository.findAll().stream().map(role->new UserRoleDto(role.getId(), role.getName())).collect(Collectors.toList());
    }
}
