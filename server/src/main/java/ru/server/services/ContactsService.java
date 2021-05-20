package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.server.models.Contacts;
import ru.server.repositories.IContactsRepository;

import java.util.List;

@Service
public class ContactsService implements IContactsService {
    @Autowired
    IContactsRepository repository;
    @Override
    public List<Contacts> getAll() {
        return repository.findAll();
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
