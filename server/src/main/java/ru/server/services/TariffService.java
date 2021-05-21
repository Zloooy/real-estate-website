package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.server.models.Tariff;
import ru.server.repositories.ITariffRepository;

import java.util.List;

@Service
public class TariffService implements ITariffService {
    @Autowired
    private ITariffRepository repository;
    @Override
    public List<Tariff> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean create(Tariff tariff) {
        repository.save(tariff);
        return true;
    }

    @Override
    public boolean update(Tariff tariff) {
        if (!repository.existsById(tariff.getId()))
        return false;
        repository.save(tariff);
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
