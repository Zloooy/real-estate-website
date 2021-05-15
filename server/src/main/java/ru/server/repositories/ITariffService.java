package ru.server.repositories;

import org.springframework.stereotype.Service;
import ru.server.models.Tariff;

import java.util.List;

@Service
public interface ITariffService {
    List<Tariff> getAll();
}
