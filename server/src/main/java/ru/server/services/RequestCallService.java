package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
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
        if (query.getPlaceType() == null || (query.getContactEmail() == null && query.getContactPhone() == null)) {
            return false;
        }
        Complex complex = null;
        String objectDescription = null;
        switch (query.getPlaceType())
        {
            case FLAT:
                Optional<Flat> flat = flatService.findById(query.getPlaceId());
                if (flat.isPresent()){
                    complex = flat.get().getComplex();
                    objectDescription = String.format("комплекс %s, Квартира на %s по цене %s", complex.getName(), flat.get().getFloor(), flat.get().getPrice());
                }
                break;
            case COMPLEX:
                complex = complexService.findById(query.getPlaceId())
                        .orElse(null);
                objectDescription = String.format("ЖК %s", complex.getName());
                break;
        }
        if (complex == null || objectDescription == null) return false;

        SimpleMailMessage message = new SimpleMailMessage();
        String mailText = String.format(
                "Поступил запрос от %s\n" +
                "Объект: %s (%s)\n" +
                        "Комментарий: %s",
                query.getName(),
                objectDescription,
                constructUrl("http://localhost:81", query.getPlaceType(), query.getPlaceId()),
                query.getComment()
        );
        message.setTo(complex.getContacts().getEmail());
        message.setSubject("Запрос на звонок");
        message.setText(mailText);
        mailSender.send(message);
        return true;
    }
    private String constructUrl(String base, RequestCallQuery.PlaceType placeType, long placeId){
        String path = null;
        switch (placeType){
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
