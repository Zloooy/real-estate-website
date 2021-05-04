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
import ru.server.models.District;
import ru.server.services.IDistrictService;

import java.util.List;

@RestController
@Api(value = "Районы", produces = "application/json")
public class DistrictRestController {
    @Autowired
    IDistrictService districtService;

    @ApiOperation(value = "Получить список районов города")
    @GetMapping(value = "/public_api/districts", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Список районов успешно получен"),
            @ApiResponse(code = 404, message = "Города с таким id не существует")
    })
public ResponseEntity<List<District>> getDistrictsByCity(@RequestParam(name="city_id") Long cityId){
    return ResponseEntity.of(districtService.getDistrictsByCityId(cityId));
}
}
