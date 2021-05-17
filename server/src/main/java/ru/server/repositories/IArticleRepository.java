package ru.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.server.models.Article;

@Repository
public interface IArticleRepository extends JpaRepository<Article, Long> {
}
