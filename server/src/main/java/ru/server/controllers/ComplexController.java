package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.server.data.ComplexQuery;
import ru.server.models.Complex;
import ru.server.services.IComplexService;

import java.util.List;

@RestController
@Api(value = "Комплексы", description = "Поиск комплексов", produces = "application/json")
public class ComplexController {
    @Autowired
    IComplexService complexService;
    @ApiOperation(value = "Получение рекламируемых городов")
    @PostMapping(value = "/public_api/advertized_complexes/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<List<Complex>> getAdvertized(@RequestBody ComplexQuery query){
        return ResponseEntity.ok(complexService.findAdvertizedByQuery(query));
    }
    @ApiOperation(value = "Получение страниц с городами")
    @PostMapping("/public_api/complexes")
    public ResponseEntity<Page<Complex>> getComplexes(@RequestBody ComplexQuery query, Pageable pageable){
        return ResponseEntity.ok(complexService.findComplexByQuery(query, pageable));
    }
}
