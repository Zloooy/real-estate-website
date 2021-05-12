package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.server.data.UserDto;
import ru.server.enums.Authority;
import ru.server.services.IUserService;

import java.util.List;

@RestController
@Api("Управление пользователями")
@Secured({Authority.CAN_MANAGE_USERS})
public class UserController {
    @Autowired
    IUserService userService;
    @ApiOperation("Получение списка всех пользователей")
    @GetMapping(value = "/api/get_all_users", produces = "application/json")
    ResponseEntity<List<UserDto>>  getAllUsers(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(userService.getAll());
    }
    @ApiOperation("Изменение ползователя")
    @PostMapping("/api/user/new")
    ResponseEntity<Boolean> createUser(@RequestHeader("Authorization") String token, @RequestBody UserDto userValue){
        return ResponseEntity.ok(userService.save(userValue));
    }
    @ApiOperation("Изменение ползователя")
    @PostMapping("/api/user/{id}")
    ResponseEntity<Boolean> createUser(@RequestHeader("Authorization") String token, @PathVariable("id") long id, @RequestBody UserDto userValue){
        userValue.setId(id);
        return ResponseEntity.ok(userService.save(userValue));
    }
}
