package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.server.enums.Authority;
import ru.server.models.Metro;
import ru.server.services.IMetroService;

import java.util.List;
import java.util.Optional;

@RestController
@Api(value="Станции метро", produces = "application/json")
public class MetroController {
    @Autowired
    IMetroService metroService;

    @ApiOperation(value = "Получить станции метро города")
    @GetMapping(value = "/public_api/metros", produces = "application/json")
    @ApiResponses(value = {
     @ApiResponse(code = 200, message = "Список станций метро успешно получен"),
     @ApiResponse(code = 404, message = "Города с таким id не существует")
    })
    ResponseEntity<List<Metro>> getCityMetros(@RequestParam(name="city_id", required = true) Long cityId){
        Optional<List<Metro>> result = metroService.getByCityId(cityId);
        return ResponseEntity.of(result);
    }
    @Secured({Authority.CAN_EDIT_METROS})
    @ApiOperation(value="Создать новую станцию метро")
    @PostMapping(value = "/api/metros/new", produces = "application/json")
    ResponseEntity<Boolean> createMetro(@RequestHeader("Authorization") String token, @RequestBody Metro newMetro){
        newMetro.setId(null);
        return ResponseEntity.ok(metroService.create(newMetro));
    }
}
