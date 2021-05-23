package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.server.models.Address;
import ru.server.models.Contacts;

import java.util.Optional;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long >, JpaSpecificationExecutor<Address> {
    Optional<Address> findByStreet(String street);

}
