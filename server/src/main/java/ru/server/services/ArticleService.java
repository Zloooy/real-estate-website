package ru.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.server.models.Article;
import ru.server.repositories.IArticleRepository;

import java.util.Optional;

@Service
public class ArticleService implements IArticleService {
    @Autowired
    IArticleRepository repository;
    @Override
    public Page<Article> findArticlePage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Article> findById(Long id)
    {
        return repository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean editById(Article toEdit) {
        if (toEdit.getId() == null || !repository.existsById(toEdit.getId())) {
            return false;
        }
        repository.save(toEdit);
        return true;
    }

    @Override
    public boolean create(Article newArticle) {
        repository.save(newArticle);
        return true;
    }
}
