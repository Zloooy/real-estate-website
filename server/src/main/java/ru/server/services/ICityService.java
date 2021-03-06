package ru.server.services;

import org.springframework.stereotype.Service;
import ru.server.models.City;

import java.util.List;

@Service
public interface ICityService {
    List<City> getAll();
}
