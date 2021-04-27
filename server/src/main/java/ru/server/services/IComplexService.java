package ru.server.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.server.data.ComplexQuery;
import ru.server.models.Complex;

import java.util.List;

@Service
public interface IComplexService {
    List<Complex> findAdvertizedByQuery(ComplexQuery query);
    Page<Complex> findComplexByQuery(ComplexQuery query, Pageable pageable);
}