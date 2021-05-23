package ru.server.services;

import org.springframework.stereotype.Service;
import ru.server.models.Address;
import ru.server.models.Contacts;

@Service

public interface IAddressService {
    Address getOne();

    void setZerroId(Long id);
}


