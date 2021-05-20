package ru.server.services;

import org.springframework.stereotype.Service;
import ru.server.models.Metro;

import java.util.List;
import java.util.Optional;

@Service
public interface IMetroService {
    Optional<List<Metro>> getByCityId(Long id);
    boolean create(Metro newMetro);
}
