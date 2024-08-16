package com.example.demo.controller;

import com.example.demo.dto.CommentDTO;
import com.example.demo.entity.CommentEntity;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.tokens.CommentToken;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // POST
    @PostMapping("/api/article/{articleId}/comments")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO dto, @PathVariable Long articleId){
        CommentDTO createDTO = commentService.createComment(articleId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(createDTO);
    }

    // GET
    @GetMapping("/api/article/{articleId}/comments")
    public ResponseEntity<List<CommentDTO>> findAllCommentsById(@PathVariable Long articleId){
        List<CommentDTO> dtos = commentService.findAllCommentsById(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    // PATCH
    @PatchMapping("/api/comment/update/{id}")
    public CommentDTO updateComment(@PathVariable Long id, @RequestBody CommentDTO dto){
        CommentDTO updateDTO = commentService.commentUpdate(id, dto);

        return updateDTO;
//        return ResponseEntity.status(HttpStatus.OK).body(updateDTO);
    }

    // DELETE
    @DeleteMapping("/api/comments/delete/{id}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable Long id){
        CommentDTO deleteComment = commentService.deleteComment(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleteComment);
    }
}
