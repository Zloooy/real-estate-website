package ru.server.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.server.models.Tariff;
import ru.server.repositories.ITariffService;

import java.util.List;

@RestController
public class TariffRestController {
    @Autowired
    private ITariffService tariffService;
    @ApiOperation(value = "Получение всех тарифов")
    @GetMapping(value = "/public_api/tariffs", produces = "application/json")
    public ResponseEntity<List<Tariff>> getAllTariffs(){
        return ResponseEntity.ok(tariffService.getAll());
    }

}
