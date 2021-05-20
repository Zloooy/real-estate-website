package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.server.data.ComplexQuery;
import ru.server.models.Complex;
import ru.server.repositories.IComplexRepository;

import javax.persistence.criteria.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ComplexService implements IComplexService{
    @Autowired
    IComplexRepository repository;
    private interface ExprBuilder {
        Expression<Boolean> apply(Root<Complex> complex, CriteriaBuilder cb, Object value);
    }
    private <T> Predicate isNull(CriteriaBuilder cb, T value){
        if (value == null) {
            return cb.and();
        }
        else {
            return cb.or();
        }
    }
    private <T> Specification<Complex> nullOr(ExprBuilder eb, T value) {
        return (complex, cq, cb)-> cb.or(isNull(cb, value), eb.apply(complex, cb, value));
    }
    private Specification<Complex> fieldLike(String fieldName, String fieldValue){
        return nullOr((complex, cb, value) -> cb.like(complex.get(fieldName), enTemp(fieldValue)), fieldValue);
    }
    private Specification<Complex> fieldBetween(String fieldName, Integer minVal, Integer maxVal) {
        return (complex, cq, cb)-> cb.or(isNull(cb, minVal), isNull(cb, maxVal), cb.between( complex.get(fieldName), minVal, maxVal));
    }
    private Specification<Complex> fieldBetween(String fieldName, Long minVal, Long maxVal) {
        return (complex, cq, cb)-> cb.or(cb.and(isNull(cb, minVal), isNull(cb, maxVal)), cb.between( complex.get(fieldName), minVal, maxVal));
    }
    private <T> Specification<Complex> fieldMatches(String field, T val){
        return nullOr((complex, cb, value)->cb.equal(complex.get(field), value), val);
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
                //fieldMatches("estateType", query.getEstateType()),
                fieldMatches("category", query.getEstateCategory()),
                fieldMatches("advertized", query.getAdvertized()),
                nullOr((complex, cb, value)->cb.equal(complex.get("address").get("city").get("id"), value), query.getCityId()),
                nullOr((complex, cb, value)->cb.equal(complex.get("address").get("metro").get("id"), value), query.getMetroId()),
                nullOr((complex, cb, value)->cb.equal(complex.get("address").get("district").get("id"), value), query.getDistrictId()),
                nullOr((complex, cb, value)->cb.ge(complex.get("price"), (Number) value), query.getPriceMin()),
                nullOr((complex, cb, value)->cb.le(complex.get("price"), (Number) value), query.getPriceMax()),
                nullOr((complex, cb, value)->cb.lessThanOrEqualTo(complex.<Date>get("deliveryDate"), (value == null) ? new Date() :((Date)value)), query.getDeliveryDate())
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

    @Override
    public Optional<Complex> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean create(Complex newComplex) {
        repository.save(newComplex);
        return true;
    }

    @Override
    public boolean update(Complex complex) {
        if(!repository.existsById(complex.getId()))
        return false;
        repository.save(complex);
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
