package ru.server.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.server.models.Article;

import java.util.Optional;

@Service
public interface IArticleService {
    Page<Article> findArticlePage(Pageable pageable);
    Optional<Article> findById(Long id);
    boolean deleteById(Long id);
    boolean editById(Article toEdit);
    boolean create(Article newArticle);
}
