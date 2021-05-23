package ru.server.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.server.models.Complex;

import java.util.Optional;

@Repository
public interface IComplexRepository extends JpaRepository<Complex, Long>, JpaSpecificationExecutor<Complex> {
    Optional<Complex> findByName(String name);


}

