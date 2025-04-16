package com.example.weeklyrest2.dto;

import com.example.weeklyrest2.domain.Article;
import com.example.weeklyrest2.domain.Comment;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleCommentResponse {
    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<CommentNoArticleResponse> comments;

    // Entity -> DTO
    public ArticleCommentResponse(Article article) {
        this.articleId = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.updatedAt = article.getUpdatedAt();

        List<Comment> commentList = article.getComments();
        this.comments = commentList.stream()
                .map(CommentNoArticleResponse::new)
                .toList();
    }
}
