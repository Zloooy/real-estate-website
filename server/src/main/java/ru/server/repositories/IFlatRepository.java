package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.server.models.Flat;

import java.util.List;

@Repository
public interface IFlatRepository extends JpaRepository<Flat, Long> {
    @Query("SELECT flat FROM Flat flat WHERE flat.complex.id = :id")
    List<Flat> findAllByComplexId(@Param("id") Long id);
    List<Flat> findAllByComplex_Id(Long id);
}
