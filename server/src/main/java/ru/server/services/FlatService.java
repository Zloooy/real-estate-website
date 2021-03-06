package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.server.data.CreationResponse;
import ru.server.models.Flat;
import ru.server.repositories.IComplexRepository;
import ru.server.repositories.IFlatRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlatService implements IFlatService {
    @Autowired
    private IComplexRepository complexRepository;
    @Autowired
    private IFlatRepository repository;
    @Override
    public Optional<List<Flat>> findComplexFlats(Long complexId) {
        if (!complexRepository.existsById(complexId))
            return Optional.empty();
        System.out.println("Complex EXISTS");
        return Optional.of(repository.findAllByComplex_Id(complexId));
    }

    @Override
    public Optional<Flat> findById(Long flatId) {
        return repository.findById(flatId);
    }

    @Override
    public CreationResponse create(Flat flat) {
        if (flat.getComplex() == null || !complexRepository.existsById(flat.getComplex().getId()))
            return new CreationResponse(false, null);
        repository.save(flat);
        return new CreationResponse(true, flat.getId());
    }

    @Override
    public boolean edit(Flat flat) {
        if (!repository.existsById(flat.getId()) || flat.getComplex() == null || !complexRepository.existsById(flat.getComplex().getId()))
            return false;
        repository.save(flat);
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
