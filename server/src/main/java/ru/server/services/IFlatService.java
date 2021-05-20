package ru.server.services;

import org.springframework.stereotype.Service;
import ru.server.data.CreationResponse;
import ru.server.models.Flat;

import java.util.List;
import java.util.Optional;

@Service
public interface IFlatService {
    Optional<List<Flat>> findComplexFlats(Long complexId);
    Optional<Flat> findById(Long flatId);
    CreationResponse create(Flat flat);
    boolean edit(Flat flat);
    boolean delete(Long id);
}
