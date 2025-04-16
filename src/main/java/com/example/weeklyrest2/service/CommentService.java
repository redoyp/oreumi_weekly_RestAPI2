package com.example.weeklyrest2.service;

import com.example.weeklyrest2.domain.Article;
import com.example.weeklyrest2.domain.Comment;
import com.example.weeklyrest2.dto.AddCommentRequest;
import com.example.weeklyrest2.repository.BlogRepository;
import com.example.weeklyrest2.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

        return commentRepository.save(new Comment(article, request.getBody()));
    }

    // 2. commentId 댓글 정보 조회
    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 글이 없습니다"));
    }

    // 3. commentId 댓글 수정
    @Transactional
    public Comment updateComment(Long commentId, AddCommentRequest request) {
        Comment comment = findCommentById(commentId);
        return comment.updateBody(request.getBody());
    }

    // 4. commentId 댓글 삭제
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
