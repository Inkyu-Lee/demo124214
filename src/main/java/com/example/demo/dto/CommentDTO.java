package com.example.demo.dto;

import com.example.demo.entity.CommentEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;

    @JsonProperty("article_id")
    private Long articleId;

    private String nickname;
    private String content;

    public static CommentDTO toDTO(CommentEntity g) {
        return new CommentDTO(
                g.getId(),
                g.getArticle().getId(),
                g.getNickname(),
                g.getContent()
        );
    }
}
