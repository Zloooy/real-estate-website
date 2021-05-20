package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.server.models.District;
import ru.server.repositories.ICityRepository;
import ru.server.repositories.IDistrictRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictService implements IDistrictService {
    @Autowired
    IDistrictRepository repository;
    @Autowired
    ICityRepository cityRepository;
    @Override
    public Optional<List<District>> getDistrictsByCityId(Long cityId) {
        if (!cityRepository.findById(cityId).isPresent())
        return Optional.empty();
        return Optional.of(repository.getAllByCityIdOrderByName(cityId));
    }

    @Override
    public Optional<District> getDistrictByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public boolean create(District district) {
        if (district.getCity() == null || !cityRepository.existsById(district.getCity().getId()))
        return false;
        repository.save(district);
        return true;
    }
}
