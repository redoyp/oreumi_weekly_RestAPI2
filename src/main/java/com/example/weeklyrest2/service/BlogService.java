package com.example.weeklyrest2.service;

import com.example.weeklyrest2.domain.Article;
import com.example.weeklyrest2.domain.Comment;
import com.example.weeklyrest2.dto.AddArticleRequest;
import com.example.weeklyrest2.dto.ArticleResponse;
import com.example.weeklyrest2.dto.ArticleResponseWithComments;
import com.example.weeklyrest2.dto.CommentResponse;
import com.example.weeklyrest2.dto.CommentResponseForArticle;
import com.example.weeklyrest2.dto.UpdateArticleRequest;
import com.example.weeklyrest2.repository.BlogRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service // 빈 등록
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository; // 빈 주입
    private final CommentService commentService;

    public Article saveArticle(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 전체 목록 조회
    public List<Article> findArticles() {
         return blogRepository.findAll();
    }

    // 단건 조회
    public Article findArticleById(Long id) {
        Optional<Article> optArticle = blogRepository.findById(id);
        return optArticle.orElseGet(Article::new);
    }

    // 단건 삭제
    public void deleteArticle(Long id) {
        blogRepository.deleteById(id);
    }

    // 전체 삭제
    public void deleteAllArticles() {
        blogRepository.deleteAll();
    }

    // 수정
    @Transactional
    public Article updateArticle(Long id, UpdateArticleRequest request) {

        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not exists id: " + id));

        article.update(request.getTitle(), request.getContent());
        return article;
    }

    // 5. 게시글과 댓글 정보를 한 번에 조회
    public ArticleResponseWithComments getArticleComments(Long articleId) {
        Article article = blogRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("해당하는 글 없음"));

        List<CommentResponseForArticle> responses = commentService.getAllCommentsByArticleId(articleId);
        return article.toDto(responses);
    }

}
