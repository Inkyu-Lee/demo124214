package com.example.demo.service;

import com.example.demo.dto.ArticleDTO;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<ArticleEntity> findAllArticle(){
        return articleRepository.findAll();
    }

    public ArticleEntity createArticle(ArticleDTO dto) {
        ArticleEntity articleEntity = dto.toEntity();
        return articleRepository.save(articleEntity);
    }

    public ArticleEntity findById(Long id) {
        ArticleEntity article = articleRepository.findById(id).
                orElseThrow( () -> new NoSuchElementException("값이 비었음"));
        return article;
    }

    @Transactional
    public ArticleEntity deleteArticleById(Long id) {
        ArticleEntity articleEntity = articleRepository.findById(id).
                orElseThrow( () -> new NoSuchElementException("값이 없음."));
        articleRepository.delete(articleEntity);
        return articleEntity;
    }

    public ArticleEntity updateArticle(ArticleDTO dto, Long id) {
        ArticleEntity article = dto.toEntity();
        ArticleEntity target = articleRepository.findById(id)
                .orElseThrow( () -> new NoSuchElementException("업데이트 할 값이 없음"));

        target.patch(article);

        articleRepository.save(target);
        return target;
    }
}
