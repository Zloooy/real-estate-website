package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.server.models.District;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDistrictRepository extends JpaRepository<District, Long> {
    Optional<District> findByName(String name);
    List<District> getAllByCityIdOrderByName(Long id);
}
