package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.server.models.Address;
import ru.server.models.Contacts;
import ru.server.repositories.IAddressRepository;
import ru.server.repositories.IContactsRepository;

import java.util.Optional;

@Service
public class AddressService implements IAddressService {
    @Autowired
    IAddressRepository repository;

    @Override
    public Address getOne(){
//        Optional<Address> found = repository.findOne(Specification.where( (Specification<Address>)(address, cq, cb) -> cb.or()));
//        if(found.isPresent()){
//            return found.get();
//        }
//        return null;
        return repository.findById(this.zerroId).get();
    }

    Long zerroId;

    @Override
    public void setZerroId(Long id) {
        zerroId = id;
    }


}
