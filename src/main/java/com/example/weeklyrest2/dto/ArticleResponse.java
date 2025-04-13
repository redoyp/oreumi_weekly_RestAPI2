package com.example.weeklyrest2.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 저장한 값의 결과
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {
    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
