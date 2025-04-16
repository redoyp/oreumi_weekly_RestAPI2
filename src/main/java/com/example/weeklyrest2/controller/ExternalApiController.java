package com.example.weeklyrest2.controller;

import com.example.weeklyrest2.service.ExternalService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExternalApiController {
    private final ExternalService externalService;

    public ExternalApiController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/api/external/articles")
    public ResponseEntity<String> externalArticle() {
        // RestTemplate
        externalService.saveArticles();

        return ResponseEntity.ok("외부 article 저장");
    }
}
