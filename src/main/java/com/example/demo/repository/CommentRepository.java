package com.example.demo.repository;

import com.example.demo.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    // 특정 게시글 댓글 조회
    @Query(value = "SELECT * " + " FROM COMMENT_ENTITY  "
            + "WHERE article_id = :articleId", nativeQuery = true)
    List<CommentEntity> findByArticleById(Long articleId);

    // 특정 닉네임 댓글 조회
    List<CommentEntity> findByNickname(String nickname);

}
