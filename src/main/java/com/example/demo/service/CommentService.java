package com.example.demo.service;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.ArticleEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public List<CommentDTO> findAllCommentsById(Long articleId) {
        List<CommentEntity> comments = commentRepository.findByArticleById(articleId);

        List<CommentDTO> dtos = new ArrayList<CommentDTO>();

//        for (int i = 0; i < comments.size(); i++) {
//            CommentEntity g = comments.get(i);
//            CommentDTO dto = CommentDTO.toDTO(g);
//            dtos.add(dto);
//        }
        return commentRepository.findByArticleById(articleId).stream()
                .map(comment -> CommentDTO.toDTO(comment))
                .collect(Collectors.toList());

    }

    public CommentDTO createComment(Long articleId, CommentDTO dto) {
        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("게시글이 없음"));

        CommentEntity comment = CommentEntity.toEntity(dto, article);

        CommentEntity createdComment = commentRepository.save(comment);

        return CommentDTO.toDTO(createdComment);
    }

    public CommentDTO commentUpdate(Long id, CommentDTO dto) {
        CommentEntity targetComment = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("댓글이 없음"));

        targetComment.patch(dto);

        CommentEntity updateComment = commentRepository.save(targetComment);

        return CommentDTO.toDTO(updateComment);
    }

    public CommentDTO deleteComment(Long id) {
        CommentEntity deleteTarget = commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("댓글 삭제 실패! 찾을 수 없음"));
        commentRepository.delete(deleteTarget);

        return CommentDTO.toDTO(deleteTarget);
    }
}
