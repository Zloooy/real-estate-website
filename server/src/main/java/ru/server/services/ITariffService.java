package ru.server.services;

import org.springframework.stereotype.Service;
import ru.server.models.Tariff;

import java.util.List;

@Service
public interface ITariffService {
    List<Tariff> getAll();
    boolean create(Tariff tariff);
    boolean update(Tariff tariff);
    boolean delete(Long id);
}
