package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import ru.server.data.UserRoleDto;
import ru.server.enums.Authority;
import ru.server.services.IRoleService;
import ru.server.services.RoleService;

import java.util.List;

@Secured({Authority.CAN_VIEW_ROLES})
@RestController
@Api(value = "Получение информации о ролях")
public class UserRoleController {
    @Autowired
    private RoleService roleService;
    @ApiOperation(value="Получение списка ролей")
    @GetMapping(value = "/api/user_roles", produces = "application/json")
    public ResponseEntity<List<UserRoleDto>> getAllUserRoles(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
