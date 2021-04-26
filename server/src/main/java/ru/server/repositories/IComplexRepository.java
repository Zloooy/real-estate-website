package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.server.models.Complex;

@Repository
public interface IComplexRepository extends JpaRepository<Complex, Long>, JpaSpecificationExecutor<Complex> {
}
