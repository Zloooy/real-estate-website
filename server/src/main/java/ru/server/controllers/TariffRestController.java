package ru.server.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.server.enums.Authority;
import ru.server.models.Tariff;
import ru.server.services.ITariffService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TariffRestController {
    @Autowired
    private ITariffService tariffService;
    @ApiOperation(value = "Получение всех тарифов")
    @GetMapping(value = "/public_api/tariffs", produces = "application/json")
    public ResponseEntity<List<Tariff>> getAllTariffs(){
        return ResponseEntity.ok(tariffService.getAll());
    }
    @ApiOperation(value = "Создание нового тарифа")
    @Secured({Authority.CAN_EDIT_TARIFFS})
    @PostMapping(value = "/api/tariffs/new", produces = "application/json")
    public ResponseEntity<Boolean> createNewTariff(@RequestHeader("Authorization") String token, @RequestBody Tariff newTariff){
        return ResponseEntity.ok(tariffService.create(newTariff));
    }
    @Secured({Authority.CAN_EDIT_TARIFFS})
    @ApiOperation(value = "Обновление информации по тарифу")
    @PostMapping(value="/api/tariffs/{id}", produces = "application/json")
    public ResponseEntity<Boolean> updateTariff(@RequestHeader("Authorization") String token, @PathVariable("id") Long id, @RequestBody Tariff tariffToReplace){
        return ResponseEntity.ok(tariffService.update(tariffToReplace));
    }
    @Secured({Authority.CAN_EDIT_TARIFFS})
    @ApiOperation(value = "Удаление тарифа")
    @DeleteMapping(value="/api/tariffs/{id}", produces = "application/json")
    public ResponseEntity<Boolean> deleteTariff(@RequestHeader("Authorization") String token, @PathVariable("id") Long id){
        return ResponseEntity.ok(tariffService.delete(id));
    }
}
