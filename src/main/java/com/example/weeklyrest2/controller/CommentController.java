package com.example.weeklyrest2.controller;

import com.example.weeklyrest2.domain.Comment;
import com.example.weeklyrest2.dto.AddCommentRequest;
import com.example.weeklyrest2.dto.CommentResponse;
import com.example.weeklyrest2.dto.UpdateCommentRequest;
import com.example.weeklyrest2.service.CommentService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // 1. 댓글 정보 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentResponse> saveComment(@PathVariable Long articleId,
                                                       @RequestBody AddCommentRequest request) {
        Comment response = commentService.saveComment(articleId, request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response.toDto());
    }

    // 2. commentId 댓글 정보 조회
    @GetMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> findCommentById(@PathVariable Long commentId) {
        Comment comment = commentService.findCommentById(commentId);

        return ResponseEntity.ok(comment.toDto());
    }

    // 3. commentId 댓글 정보 수정
    @PutMapping("/api/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentId,
                                                         @RequestBody UpdateCommentRequest request) {
        Comment comment = commentService.updateComment(commentId, request);

        return ResponseEntity.ok(comment.toDto());
    }

    // 4. commentId 댓글 삭제
    @DeleteMapping("/api/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);

        return ResponseEntity.ok().build();
    }
}
