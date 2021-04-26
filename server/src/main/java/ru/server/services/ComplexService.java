package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.server.data.ComplexQuery;
import ru.server.models.Complex;
import ru.server.repositories.IComplexRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import java.util.Arrays;
import java.util.List;

@Service
public class ComplexService implements IComplexService{
    @Autowired
    IComplexRepository repository;
    private Specification<Complex> fieldLike(String fieldName, String fieldValue){
        return (complex, cq, cb)-> cb.or(isNull(cb, fieldValue), cb.like(complex.get(fieldName), enTemp(fieldValue)));
    }
    private Specification<Complex> fieldBetween(String fieldName, Integer minVal, Integer maxVal) {
        return (complex, cq, cb)-> cb.or(cb.and(isNull(cb, minVal), cb.literal(maxVal).isNull()), cb.between( complex.get(fieldName), minVal, maxVal));
    }
    private Specification<Complex> fieldBetween(String fieldName, Long minVal, Long maxVal) {
        return (complex, cq, cb)-> cb.or(cb.and(isNull(cb, minVal), isNull(cb, maxVal)), cb.between( complex.get(fieldName), minVal, maxVal));
    }
    private <T> Specification<Complex> fieldMatches(String fieldName, T value){
        return (complex, cq, cb)-> cb.or(isNull(cb, value), cb.equal(complex.get(fieldName), value));
    }
    private <T> Predicate isNull(CriteriaBuilder cb, T value){
        if (value == null) {
            return cb.and();
        }
        else {
            return cb.or();
        }
    }
    private String enTemp(String s){
        return "%" + s + "%";
    }
    private Specification<Complex> and(Specification<Complex>... specifications){
        return Arrays.stream(specifications).reduce(Specification::and).orElse(null);
    }
private Specification<Complex> generateSpecification(ComplexQuery query){
        return Specification.where(and(
                fieldLike("name", query.getName()),
                fieldMatches("id", query.getId()),
                fieldMatches("estateType", query.getEstateType()),
                fieldMatches("category", query.getEstateCategory()),
                fieldMatches("advertized", query.getAdvertized())
                //@TODO add all filters
        ));
}
    @Override
    public List<Complex> findAdvertizedByQuery(ComplexQuery query) {
        query.setAdvertized(true);
        return repository.findAll(generateSpecification(query));
    }

    @Override
    public Page<Complex> findComplexByQuery(ComplexQuery query, Pageable pageable) {
        query.setAdvertized(null);
        return repository.findAll(generateSpecification(query), pageable);
    }
}
