package ru.server.services;

import org.springframework.stereotype.Service;
import ru.server.models.Contacts;

import java.util.List;

@Service
public interface IContactsService {
    List<Contacts> getAll();
    boolean create(Contacts contacts);
    boolean delete(Long id);
 }
