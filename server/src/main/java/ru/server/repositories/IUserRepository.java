package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.server.models.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Query("FROM User where login = :username")
    User findByUsername(String username);
}
