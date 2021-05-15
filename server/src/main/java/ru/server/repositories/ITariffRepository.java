package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.server.models.Tariff;

@Repository
public interface ITariffRepository extends JpaRepository<Tariff, Long> {
}
