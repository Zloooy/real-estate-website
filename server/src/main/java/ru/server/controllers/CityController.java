package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.server.models.City;
import ru.server.services.ICityService;

import java.util.List;

@RestController
@Api(value="Города", description="Получение списка городов", produces = "application/json")
public class CityController {
    @Autowired
    private ICityService cityService;
    @ApiOperation(value = "Получить все города")
    @GetMapping(value = "/public_api/cities", produces = "application/json")
    @ApiResponses(value={
            @ApiResponse(code=200, message = "Список городов успешно получен"),
            @ApiResponse(code=500, message = "Внутренняя ошибка сервера")
    })
    public ResponseEntity<List<City>> getAllCities() {
        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK);
    }
}
