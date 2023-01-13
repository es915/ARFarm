package com.example.arfarmPrSite.repository;

import com.example.arfarmPrSite.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
