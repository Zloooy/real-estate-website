package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.server.models.User;

@Repository
public interface IAuthorityRepository extends JpaRepository<User.Role.Authority, Long> {
    User.Role.Authority getByName(String name);
}
