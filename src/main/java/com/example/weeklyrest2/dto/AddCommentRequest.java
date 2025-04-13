package com.example.weeklyrest2.dto;

import com.example.weeklyrest2.domain.Article;
import com.example.weeklyrest2.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentRequest {
    private String body;

    public Comment toEntity(Article article) {
        return new Comment(article, body);
    }
}
