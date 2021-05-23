package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.server.data.CreationResponse;
import ru.server.models.Flat;
import ru.server.services.IFlatService;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@Api(value="Квартиры", description = "Поиск квартир", produces = "application/json")
public class FlatController {
    @Autowired
    IFlatService flatService;
    @ApiOperation(value = "Получение квартиры по id")
    @GetMapping(value = "/public_api/flat/{id}", produces = "application/json")
    ResponseEntity<Flat> getFlatById(@PathVariable("id") Long id){
        return ResponseEntity.of(flatService.findById(id));
    }

    @ApiOperation(value = "Получение списка квартир комплекса")
    @GetMapping(value = "/public_api/flats_by_complex/{id}", produces = "application/json")
    ResponseEntity<List<Flat>> getComplexFlats(@PathVariable("id") Long complexId){
        Optional<List<Flat>> res = flatService.findComplexFlats(complexId);
        System.out.println("complexFlats has value");
        System.out.println(res.isPresent());
        if (res.isPresent()) {
            System.out.println("complexFlatsList in not empty");
            System.out.println(res.get().size());
            System.out.println(res.get());
        }
        return ResponseEntity.of(res);
        //return ResponseEntity.of(flatService.findComplexFlats(complexId));
    }
    @ApiOperation("Удаление квартиры")
    @DeleteMapping(value = "/api/flat/{id}", produces = "application/json")
    ResponseEntity<Boolean> deleteFlat(@RequestHeader("Authorization") String token, @PathVariable("id") Long id){
        return ResponseEntity.ok(flatService.delete(id));
    }
    @ApiOperation("Coздание новой квартиры")
    @PostMapping(value = "/api/flat/new", produces = "application/json")
    ResponseEntity<CreationResponse> createFlat(@RequestHeader("Authorization") String token, @RequestBody Flat newFlat){
        return ResponseEntity.ok(flatService.create(newFlat));
    }
    @ApiOperation(("Редактирование сущестувующей квартиры"))
    @PostMapping(value="/api/flat/{id}", produces = "application/json")
    ResponseEntity<Boolean> editFlat(@RequestHeader("Authorization") String token, @PathVariable("id") Long id, @RequestBody Flat toModify){
        toModify.setId(id);
        return ResponseEntity.ok(flatService.edit(toModify));
    }
}
