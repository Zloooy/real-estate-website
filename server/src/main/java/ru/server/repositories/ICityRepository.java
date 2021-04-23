package ru.server.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.server.models.City;

import java.util.List;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
}
