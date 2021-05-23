package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.server.models.Contacts;

import java.util.Optional;

@Repository
public interface IContactsRepository extends JpaRepository<Contacts, Long>, JpaSpecificationExecutor<Contacts> {
    Optional<Contacts> getByName(String name);
}
