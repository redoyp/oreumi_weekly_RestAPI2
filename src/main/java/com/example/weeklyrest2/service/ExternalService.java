package com.example.weeklyrest2.service;

import com.example.weeklyrest2.dto.AddArticleRequest;
import com.example.weeklyrest2.dto.ArticleResponse;
import com.example.weeklyrest2.dto.PostContent;
import com.example.weeklyrest2.repository.BlogRepository;
import com.example.weeklyrest2.repository.CommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final BlogRepository blogRepository;
    private final CommentRepository commentRepository;

    public void saveArticles() {
        String url = "https://jsonplaceholder.typicode.com/posts";

        ResponseEntity<List<AddArticleRequest>> response = restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {}
        );

        List<AddArticleRequest> articles = response.getBody();
        for(AddArticleRequest article : articles) {
            blogRepository.save(article.toEntity());
        }
    }

//    public void call() {
//        String url = "https://jsonplaceholder.typicode.com/posts";
//        //RestTemplate
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<List<PostContent>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
//
//        log.info("code: {}", responseEntity.getStatusCode());   // code: 200 OK
//        List<PostContent> postContent = responseEntity.getBody();
//        log.info("postContent: {}", postContent);   // id, id ,id, ... (PostContent 에서 toString() 을 그렇게 출력하도록 override함)
//    }
}
