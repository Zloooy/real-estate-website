package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.server.models.Contacts;
import ru.server.services.IContactsService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@Api(value="Контакты", description = "Работа с контактами")
public class ContactsController {
    @Autowired
    IContactsService contactsService;
    @ApiOperation(value="Получение списка всех контактов")
    @GetMapping(value = "/api/contacts", produces = "application/json")
    public ResponseEntity<List<Contacts>> getAllContacts(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(contactsService.getAll());
    }
    @ApiOperation(value = "Создание нового контакта")
    @PostMapping(value = "/api/contacts/new", produces = "application/json")
    public ResponseEntity<Boolean> createNewContacts(@RequestHeader("Authorization") String token, @RequestBody Contacts newContacts){
        newContacts.setId(null);
        return ResponseEntity.ok(contactsService.create(newContacts));
    }
    @ApiOperation(value="Удаление контактов")
    @DeleteMapping(value = "/api/contacts/{id}")
    public ResponseEntity<Boolean> deleteContact(@RequestHeader("Authorization") String token, @PathVariable("id") Long id){
        return ResponseEntity.ok(contactsService.delete(id));
    }


}
