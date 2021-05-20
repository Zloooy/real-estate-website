package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.server.data.UserDto;
import ru.server.enums.Authority;
import ru.server.services.ITokenService;
import ru.server.services.IUserService;

import java.util.List;

@RestController
@Api("Управление пользователями")
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    private ITokenService tokenService;

    @Secured({Authority.CAN_MANAGE_USERS})
    @ApiOperation("Получение списка всех пользователей")
    @GetMapping(value = "/api/get_all_users", produces = "application/json")
    ResponseEntity<List<UserDto>>  getAllUsers(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.getAll());
    }
    @Secured({Authority.CAN_MANAGE_USERS})
    @ApiOperation("Изменение ползователя")
    @PostMapping("/api/user/new")
    ResponseEntity<Boolean> createUser(@RequestHeader("Authorization") String token, @RequestBody UserDto userValue){
        userValue.setId(null);
        return ResponseEntity.ok(userService.save(userValue));
    }
    @Secured({Authority.CAN_MANAGE_USERS})
    @ApiOperation("Изменение ползователя")
    @PostMapping("/api/user/{id}")
    ResponseEntity<Boolean> createUser(@RequestHeader("Authorization") String token, @PathVariable("id") long id, @RequestBody UserDto userValue){
        userValue.setId(id);
        return ResponseEntity.ok(userService.save(userValue));
    }
    @ApiOperation("Получение разрешений пользователя")
    @PostMapping(value = "/api/user/authorities", produces="application/json")
    ResponseEntity<List<String>> getUserAuthorities(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.getUserAuthorities(tokenService.getLoginFromToken(token.substring(7))));
    }
}
