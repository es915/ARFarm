package com.example.arfarmPrSite.service;

import com.example.arfarmPrSite.domain.Article;
import com.example.arfarmPrSite.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 게시물 클릭시 좋아요 +1
    @Transactional
    public Long clickOnArticle(Long id) {
        Article article = articleRepository.findById(id).get();
        article.plusLike();
        return article.getId();
    }

    // 게시물 다 뽑아오기 -> DB에 미리 4개만 저장하여 사용함
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

}
