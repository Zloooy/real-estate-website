package ru.server.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.server.data.ComplexQuery;
import ru.server.data.CreationResponse;
import ru.server.models.Complex;

import java.util.List;
import java.util.Optional;

@Service
public interface IComplexService {
    List<Complex> findAdvertizedByQuery(ComplexQuery query);
    Page<Complex> findComplexByQuery(ComplexQuery query, Pageable pageable);
    Optional<Complex> findById(Long id);
    CreationResponse create(Complex newComplex);
    boolean update(Complex complex);
    boolean delete(Long id);
}
