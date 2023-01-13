package com.example.arfarmPrSite.service;

import com.example.arfarmPrSite.domain.Article;
import com.example.arfarmPrSite.domain.ArticleState;
import com.example.arfarmPrSite.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.arfarmPrSite.domain.Article.createNewArticle;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    // 게시물 저장
    @Transactional
    public Long save(String description) {
        Article article = createNewArticle(description);
        articleRepository.save(article);
        return article.getId();
    }

    // 게시물 좋아요 +1
    @Transactional
    public Long plusLike(Long id) {
        Article article = findArticle(id);
        article.plusLike();
        return article.getId();
    }

    // 게시물 좋아요 리셋
    @Transactional
    public Long resetLike(Long id) {
        Article article = findArticle(id);
        article.resetLike();
        return article.getId();
    }

    // 게시물의 게시 여부 바꾸기 OPEN, CLOSE
    @Transactional
    public Long changeState(Long id) {
        Article article = findArticle(id);
        article.changeState();
        return article.getId();
    }

    // 게시물 게시 종료
    @Transactional
    public Long postingDone(Long id) {
        Article article = findArticle(id);
        article.setUnregisteredDate();
        return article.getId();
    }

    // ---- 조회 ----
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findOne(Long id) {
        return articleRepository.findById(id);
    }

    // 게시물 게시 여부 확인
    public ArticleState checkArticleState(Long id) {
        return findArticle(id).getArticleState();
    }

    // 특정 게시물 찾기
    private Article findArticle(Long id) {
        try {
            Optional<Article> article = articleRepository.findById(id); // optional null 값 체킹
        } catch (NoSuchElementException e) { // null 이면 예외 던지기
            Article article =  articleRepository.findById(id)
                    .orElseThrow(IllegalArgumentException::new);
        }

        return articleRepository.findById(id).get(); // 예외가 없다면 통과
    }






}
