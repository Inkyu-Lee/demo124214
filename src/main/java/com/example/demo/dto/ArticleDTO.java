package com.example.demo.dto;

import com.example.demo.entity.ArticleEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {

    private String title;
    private String content;

    public ArticleEntity toEntity() {
        ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setTitle(title);
        articleEntity.setContent(content);
        return articleEntity;
    }
}
