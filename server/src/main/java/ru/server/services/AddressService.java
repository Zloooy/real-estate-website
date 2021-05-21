package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.server.models.Address;
import ru.server.repositories.IAddressRepository;

import java.util.Optional;

@Service
public class AddressService implements IAddressService {
    @Autowired
    IAddressRepository addressRepository;
    @Override
    public Optional<Address> saveAddress(Address address) {
        if (address == null)
            return Optional.empty();
            addressRepository.save(address);
        return Optional.of(address);
    }
}
