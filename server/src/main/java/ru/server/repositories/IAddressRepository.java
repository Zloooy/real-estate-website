package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.server.models.Address;

import java.util.Optional;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByStreet(String street);
}
