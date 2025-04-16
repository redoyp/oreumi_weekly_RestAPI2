package com.example.weeklyrest2.dto;

import com.example.weeklyrest2.domain.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentNoArticleResponse {
    private Long commentId;
    private Long articleId;
    private String body;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public CommentNoArticleResponse(Comment comment) {
        this.commentId = comment.getId();
        this.articleId = comment.getArticle().getId();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();
    }
}
