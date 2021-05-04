package ru.server.services;

import org.springframework.stereotype.Service;
import ru.server.models.District;

import java.util.List;
import java.util.Optional;

@Service
public interface IDistrictService {
    Optional<List<District>> getDistrictsByCityId(Long cityId);
    Optional<District> getDistrictByName(String name);
}
