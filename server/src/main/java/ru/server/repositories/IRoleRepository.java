package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.server.models.User;

@Repository
public interface IRoleRepository extends JpaRepository<User.Role, Long> {
    User.Role getByName(String name);
}
