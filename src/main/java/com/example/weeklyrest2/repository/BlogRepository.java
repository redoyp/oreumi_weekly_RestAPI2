package com.example.weeklyrest2.repository;

import com.example.weeklyrest2.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // bean 등록
public interface BlogRepository extends JpaRepository<Article, Long> {
}
