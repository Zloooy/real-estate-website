package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.server.data.RequestCallQuery;
import ru.server.services.IRequestCallService;

@RestController
@Api(value="Email-рассылка 'Заказать звонок'")
@CrossOrigin(origins = "*")
public class RequestCallController {
    @Qualifier("requestCallService")
    @Autowired
    IRequestCallService service;
    @ApiOperation("Отправка запроса на звонок")
    @PutMapping("/public_api/request_call")
    public void sendCallRequest(@RequestBody RequestCallQuery query){
        System.out.println("sendCallRequest called");
        System.out.println(service.sendMail(query));
    }
}
