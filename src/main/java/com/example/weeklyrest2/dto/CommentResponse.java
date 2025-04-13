package com.example.weeklyrest2.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {
    private Long commentId;
    private Long articleId;
    private String body;
    private LocalDateTime createdAt;
    private ArticleResponse article;
}
