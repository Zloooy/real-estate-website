package ru.server.services;

import org.springframework.stereotype.Service;
import ru.server.models.Address;

import java.util.Optional;

@Service
public interface IAddressService {
    Optional<Address> saveAddress(Address address);
}
