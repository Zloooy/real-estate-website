package ru.server.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.server.data.CreationResponse;
import ru.server.enums.Authority;
import ru.server.models.Article;
import ru.server.services.IArticleService;

@RestController
@Api(value="Статьи", description = "Получение статей", produces = "application/json")
public class ArticleController {
    @Autowired
    IArticleService articleService;
    @ApiOperation(value = "Получение статьи с заданным id")
    @GetMapping(value = "/public_api/article/{id}", produces = "application/json")
    public ResponseEntity<Article> findArticleById(@PathVariable("id") Long id){
        return ResponseEntity.of(articleService.findById(id));
    }
    @Secured({Authority.CAN_EDIT_ARTICLES})
    @ApiOperation(value="Удаление статьи по id")
    @DeleteMapping(value = "/api/article/{id}", produces = "application/json")
    public ResponseEntity<Boolean> deleteArticleById(@RequestHeader("Authorization") String token, @PathVariable("id") Long id){
        return ResponseEntity.ok(articleService.deleteById(id));
    }
    @Secured({Authority.CAN_EDIT_ARTICLES})
    @ApiOperation(value = "Обновить статью с id")
    @PostMapping(value = "/api/article/new", produces = "application/json")
    public ResponseEntity<CreationResponse> createArticle(@RequestHeader("Authorization") String token, @RequestBody Article article){
        return ResponseEntity.ok(articleService.create(article));
    }
    @Secured({Authority.CAN_EDIT_ARTICLES})
    @ApiOperation(value = "Обновить статью с id")
    @PostMapping(value = "/api/article/{id}", produces = "application/json")
    public ResponseEntity<Boolean> editArticleById(@RequestHeader("Authorization") String token, @PathVariable("id") Long id, @RequestBody Article article){
        article.setId(id);
        return ResponseEntity.ok(articleService.editById(article));
    }
    @ApiOperation(value = "Получить страницу статей")
    @GetMapping(value = "/public_api/article", produces = "application/json")
    public ResponseEntity<Page<Article>> getArticles(Pageable pageable){
        return ResponseEntity.ok(articleService.findArticlePage(pageable));
    }

}
