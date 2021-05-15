package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.server.data.RequestCallQuery;
import ru.server.models.Complex;
import ru.server.models.Flat;

import java.util.Optional;

@Service
public class RequestCallService implements IRequestCallService{

    @Autowired
    public JavaMailSender mailSender;
    @Autowired
    IFlatService flatService;
    @Autowired
    IComplexService complexService;
    @Override
    public boolean sendMail(RequestCallQuery query) {
        if (query.getCallRequestType() == null || (query.getContactEmail() == null && query.getContactPhone() == null)) {
            System.out.println(query.getCallRequestType());
            System.out.println(query.getContactPhone());
            System.out.println(query.getContactEmail());
            System.out.println("callRequestType or contactEmail null");
            return false;
        }
        String requestText = null;
        String requestTheme = null;
        String messageToAddress;
        Complex complex = null;
        String objectDescription = null;
        switch (query.getCallRequestType())
        {
            case FLAT:
                Optional<Flat> flat = flatService.findById(query.getPlaceId());
                if (flat.isPresent()){
                    complex = flat.get().getComplex();
                    objectDescription = String.format("комплекс %s, Квартира на %s по цене %s", complex.getName(), flat.get().getFloor(), flat.get().getPrice());
                    requestTheme = "Консультация по объекту недвижимости";
                }
                break;
            case COMPLEX:
                complex = complexService.findById(query.getPlaceId())
                        .orElse(null);
                if (complex != null) {
                    objectDescription = String.format("ЖК %s", complex.getName());
                    requestTheme = "Консультация по объекту недвижимости";
                }
                break;
            case WANT_TO_BUY:
                requestTheme = "Консультация по покупке недвижимости";
                requestText = "";
                break;
            case WANT_TO_SELL:
                requestTheme = "Консультация по продаже недвижимости";
                requestText = "";
                break;
        }
        if (requestTheme == null) {
            System.out.println("requestTheme not present");
            return false;
        }
        if (objectDescription != null){
            requestText =  String.format("Объект: %s (%s)\n",
                    objectDescription,
                    constructUrl("http://localhost:81", query.getCallRequestType(), query.getPlaceId())
                    );
            messageToAddress = complex.getContacts().getEmail();
        }
        else {
            messageToAddress = "gu1999yra@yandex.ru";
        }
        StringBuilder messageContactBuilder = new StringBuilder("Данные для связи:\n");
        if (query.getContactPhone() != null) {
            messageContactBuilder.append(String.format("Телефон:\t%s\n", query.getContactPhone()));
        }
        if (query.getContactEmail() != null) {
            messageContactBuilder.append(String.format("Email:\t%s\n", query.getContactEmail()));
        }
        SimpleMailMessage message = new SimpleMailMessage();
        String mailText = String.format("Поступил запрос от %s\n" +
                "Тема:\t %s\n"+
                "%s\n" +
                "%s\n" +
                "Комментарий:\t%s",
                query.getName(),
                requestTheme,
                requestText,
                messageContactBuilder,
                query.getComment());
        message.setTo(messageToAddress);
        message.setSubject("Запрос на звонок");
        message.setText(mailText);
        mailSender.send(message);
        return true;
    }
    private String constructUrl(String base, RequestCallQuery.CallRequestType callRequestType, long placeId){
        String path = null;
        switch (callRequestType){
            case COMPLEX:
                path = "complex";
                break;
            case FLAT:
                path = "flat";
                break;
        }
        if (path == null)
            throw new NullPointerException();
        return String.format("%s/%s/%s", base, path, placeId);
    }
}
