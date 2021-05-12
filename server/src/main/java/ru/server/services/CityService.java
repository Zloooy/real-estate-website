package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.server.models.City;
import ru.server.repositories.ICityRepository;

import java.util.List;

@Service
public class CityService implements ICityService{
    @Autowired
    private ICityRepository repository;
    @Override
    public List<City> getAll() {
        return repository.findAll();
    }
}
