package ru.server.services;

import org.hibernate.criterion.CriteriaQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.server.models.Contacts;
import ru.server.repositories.IContactsRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@Service
public class ContactsService implements IContactsService {
    @Autowired
    IContactsRepository repository;
    @Override
    public List<Contacts> getAll() {
        return repository.findAll();
    }

    @Override
    public Contacts getOne(){
       Optional<Contacts> found = repository.findOne(Specification.where( (Specification<Contacts>)(contacts, cq, cb) -> cb.and()));
        if(found.isPresent()){
            return found.get();
        }
        return null;
    }

    @Override
    public boolean create(Contacts contacts) {
        repository.save(contacts);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        if (!repository.existsById(id))
        return false;
        repository.deleteById(id);
        return true;
    }
}
