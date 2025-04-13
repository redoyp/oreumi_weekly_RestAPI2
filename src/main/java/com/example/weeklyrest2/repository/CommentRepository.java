package com.example.weeklyrest2.repository;

import com.example.weeklyrest2.domain.Article;
import com.example.weeklyrest2.domain.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);
}
