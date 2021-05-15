package ru.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.server.models.Tariff;

import java.util.List;

@Service
public class TariffService implements ITariffService {
    @Autowired
    private ITariffRepository repository;
    @Override
    public List<Tariff> getAll() {
        return repository.findAll();
    }
}
