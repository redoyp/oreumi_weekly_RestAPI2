package com.example.weeklyrest2.domain;

import com.example.weeklyrest2.dto.ArticleResponse;
import com.example.weeklyrest2.dto.ArticleResponseWithComments;
import com.example.weeklyrest2.dto.CommentResponse;
import com.example.weeklyrest2.dto.CommentResponseForArticle;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
// 엔티티의 변화를 감지하여 엔티티와 매핑된 테이블의 데이터 조작
// AuditingEntityListener 클래스는 엔티티 수정, 영속 이벤트를 감지
// 스프링 부트 메인 클래스에 @EnableJpaAuditing 어노테이션이 있어야 JPA Auditing 기능이 활성화 됨
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse toDto() {
        return new ArticleResponse(id, title, content, createdAt, updatedAt);
    }

    public ArticleResponseWithComments toDto(List<CommentResponseForArticle> comments) {
        return new ArticleResponseWithComments(id, title, content, createdAt, updatedAt, comments);
    }
}
