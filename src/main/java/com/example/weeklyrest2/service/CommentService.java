package com.example.weeklyrest2.service;

import com.example.weeklyrest2.domain.Article;
import com.example.weeklyrest2.domain.Comment;
import com.example.weeklyrest2.dto.AddCommentRequest;
import com.example.weeklyrest2.dto.CommentResponse;
import com.example.weeklyrest2.dto.CommentResponseForArticle;
import com.example.weeklyrest2.dto.UpdateCommentRequest;
import com.example.weeklyrest2.repository.BlogRepository;
import com.example.weeklyrest2.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BlogRepository blogRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository blogRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
    }

    // 1. 댓글 정보 생성
    public Comment saveComment(Long articleId, AddCommentRequest request) {
        Article article = blogRepository.findById(articleId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 글이 없습니다"));

        return commentRepository.save(request.toEntity(article));
    }

    // 2. commentId 댓글 정보 조회
    public Comment findCommentById(Long commentId) {
        Optional<Comment> optComment = commentRepository.findById(commentId);
        return optComment.orElseGet(Comment::new);
    }

    // 3. commentId 댓글 수정
    @Transactional
    public Comment updateComment(Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 댓글 없음"));

        comment.update(request.getBody());
        return comment;
    }

    // 4. commentId 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    // 5.1 게시글 id로 댓글 모두 조회
    public List<CommentResponseForArticle> getAllCommentsByArticleId(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        return comments.stream()
                .map(Comment::toDtoForArticle)
                .toList();
    }
}
