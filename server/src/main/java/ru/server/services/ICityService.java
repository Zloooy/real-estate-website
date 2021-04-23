package ru.server.services;

import ru.server.models.City;

import java.util.List;

public interface ICityService {
    List<City> getAll();
}
