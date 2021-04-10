package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.server.data.Auth;
import ru.server.services.ITokenService;
import ru.server.services.IUserService;

@RestController
@Api(value="Аутентификация", description = "Возвращение токена в обмен на учётные данные.", consumes = "application/json", produces = "application/json")
public class AuthController {
    @Autowired
    IUserService userService;
    @Autowired
    ITokenService tokenService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @ApiOperation(value = "Аутентификация")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = "Успешно авторизован"),
            @ApiResponse(code=401, message = "Ошибка авторизации")
    })
    @PostMapping(value = "/auth", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Auth.Response> auth(@RequestBody Auth.Request request) {
        UserDetails u = userService.loadUserByUsername(request.getLogin());
        if (passwordEncoder.matches(request.getPassword(), u.getPassword())) {
            return new ResponseEntity<Auth.Response>(new Auth.Response(tokenService.getToken(u.getUsername())), HttpStatus.OK);
        }
        return new ResponseEntity<Auth.Response>((Auth.Response) null, HttpStatus.UNAUTHORIZED);
    }
}
