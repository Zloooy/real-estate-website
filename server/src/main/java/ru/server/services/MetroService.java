package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.server.models.Metro;
import ru.server.repositories.ICityRepository;
import ru.server.repositories.IMetroRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MetroService implements IMetroService {
    @Autowired
    private IMetroRepository repository;
    @Autowired
    private ICityRepository cityRepository;
    @Override
    public Optional<List<Metro>> getByCityId(Long id) {
        if (!cityRepository.findById(id).isPresent())
            return Optional.empty();
        return Optional.of(repository.findAllByCityIdOrderByName(id));
    }

    @Override
    public boolean create(Metro newMetro) {
        if (newMetro.getCity() == null || !cityRepository.existsById(newMetro.getCity().getId()))
        return false;
        repository.save(newMetro);
        return true;
    }
}
