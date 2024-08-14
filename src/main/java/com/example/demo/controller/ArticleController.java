package com.example.demo.controller;

import com.example.demo.dto.ArticleDTO;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    // POST
    @PostMapping("/api/create/article")
    public ResponseEntity<ArticleEntity> createArticle(@RequestBody ArticleDTO dto){

        if ( dto == null ){
            throw new NoSuchElementException("DTO값을 찾을 수 없음.");
        }

        ArticleEntity articleEntity = articleService.createArticle(dto);

        return (articleEntity == null) ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(articleEntity);

    }

    // GET
    @GetMapping("/api/article/all")
    public List<ArticleEntity> getAllArticle(){
        return articleService.findAllArticle();
    }

    @GetMapping("/api/article/{id}")
    public ResponseEntity<ArticleEntity> getArticleById(@PathVariable Long id){
        ArticleEntity article = articleService.findById(id);

        return (article != null) ? ResponseEntity.status(HttpStatus.OK).body(article) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/article/delete/{id}")
    public ResponseEntity<ArticleEntity> deleteArticle(@PathVariable Long id){
        ArticleEntity article = articleService.deleteArticleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(article);
    }

    // PATCH

    @PatchMapping("/api/article/update/{id}")
    public ResponseEntity<ArticleEntity> updateArticle(@RequestBody ArticleDTO DTO, @PathVariable Long id){
        if ( DTO == null){
            throw new NoSuchElementException("DTO가 비었음.");
        }

        ArticleEntity article = articleService.updateArticle(DTO ,id);

        return (article != null) ? ResponseEntity.status(HttpStatus.OK).body(article) :
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
