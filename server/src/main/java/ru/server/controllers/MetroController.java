package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
