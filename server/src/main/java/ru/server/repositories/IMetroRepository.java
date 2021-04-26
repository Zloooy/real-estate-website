package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.server.models.Metro;

import java.util.Optional;

@Repository
public interface IMetroRepository extends JpaRepository<Metro, Long> {
    public Optional<Metro> findByName(String name);
}
