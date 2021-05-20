package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.server.data.ComplexQuery;
import ru.server.data.CreationResponse;
import ru.server.enums.Authority;
import ru.server.models.Complex;
import ru.server.models.User;
import ru.server.services.IComplexService;
import ru.server.services.ITokenService;
import ru.server.services.IUserService;

import java.util.List;

@RestController
@Api(value = "Комплексы", description = "Поиск комплексов", produces = "application/json")
public class ComplexController {
    @Autowired
    IComplexService complexService;
    @Autowired
    IUserService userService;
    @Autowired
    ITokenService tokenService;
    @ApiOperation(value = "Получение рекламируемых городов")
    @PostMapping(value = "/public_api/advertized_complexes/", produces = "application/json")
    public ResponseEntity<List<Complex>> getAdvertized(@RequestBody ComplexQuery query){
        return ResponseEntity.ok(complexService.findAdvertizedByQuery(query));
    }
    @ApiOperation(value = "Получение страниц с городами")
    @PostMapping(value = "/public_api/complexes", produces = "application/json")
    public ResponseEntity<Page<Complex>> getComplexes(@RequestBody ComplexQuery query, Pageable pageable){
        return ResponseEntity.ok(complexService.findComplexByQuery(query, pageable));
    }
    @ApiOperation(value="Комплекс с заданным id")
    @GetMapping(value="/public_api/complex/{id}", produces = "application/json")
    public ResponseEntity<Complex> findById(@PathVariable("id") Long id){
        return ResponseEntity.of(complexService.findById(id));
    }
    @Secured({Authority.CAN_MANAGE_COMPLEXES})
    @ApiOperation(value = "Создание комплекса")
    @PostMapping(value = "/api/complexes/new", produces = "application/json")
    public ResponseEntity<CreationResponse> createNewComplex(@RequestHeader("Authorization") String token, @RequestBody Complex newComplex){
        newComplex.setId(null);
        newComplex.setAuthor((User) userService.loadUserByUsername(tokenService.getLoginFromToken(token.substring(7))));
        return ResponseEntity.ok(complexService.create(newComplex));
    }
    @Secured({Authority.CAN_MANAGE_COMPLEXES})
    @ApiOperation(value="Редактирование комплекса")
    @PostMapping(value = "/api/complexes/{id}", produces = "application/json")
    public ResponseEntity<Boolean> editComplex(@RequestHeader("Authorization") String token, @PathVariable("id") Long id, @RequestBody Complex toReplace){
        toReplace.setId(id);
        toReplace.setAuthor((User) userService.loadUserByUsername(tokenService.getLoginFromToken(token.substring(7))));
        return ResponseEntity.ok(complexService.update(toReplace));
    }
    @Secured({Authority.CAN_MANAGE_COMPLEXES})
    @ApiOperation("Удаление комплекса")
    @DeleteMapping(value = "/api/complexes/{id}", produces = "application/json")
    public ResponseEntity<Boolean> deleteComplex(@RequestHeader("Authorization") String token, @PathVariable("id") Long id){
        return ResponseEntity.ok(complexService.delete(id));
    }
}
