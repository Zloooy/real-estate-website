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
        switch (query.getPlaceType())
        {
            case FLAT:
                Optional<Flat> flat = flatService.findById(query.getPlaceId());
                if (flat.isPresent()){
                    complex = flat.get().getComplex();
                }
                break;
            case COMPLEX:
                complex = complexService.findById(query.getPlaceId())
                        .orElse(null);
                break;
        }
        if (complex == null) return false;
        SimpleMailMessage message = new SimpleMailMessage();
        String mailText = String.format(
                "Заявка от %s\n" +
                "Название комплекса: %s",
                query.getName(),
                complex.getName()
        );
        message.setTo(complex.getContacts().getEmail());
        message.setSubject("Запрос на звонок");
        message.setText(mailText);
        mailSender.send(message);
        return true;
    }
}
