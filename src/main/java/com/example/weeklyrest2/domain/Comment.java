package com.example.weeklyrest2.domain;

import com.example.weeklyrest2.dto.CommentResponse;
import com.example.weeklyrest2.dto.CommentResponseForArticle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String body;

    @CreatedDate
    private LocalDateTime createdAt;

    public Comment(Article article, String body) {
        this.article = article;
        this.body = body;
    }

    public CommentResponse toDto() {
        return new CommentResponse(id, article.getId(), body, createdAt, article.toDto());
    }

    public CommentResponseForArticle toDtoForArticle() {
        return new CommentResponseForArticle(id, article.getId(), body, createdAt);
    }

    public void update(String body) {
        this.body = body;
    }
}
