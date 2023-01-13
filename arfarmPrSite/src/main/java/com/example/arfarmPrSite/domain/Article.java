package com.example.arfarmPrSite.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    @Column(nullable = false)
    private int like;

    @Column(nullable = false)
    private String description; // 게시물을 설명할 약간의 설명

    @Column(nullable = false)
    private ArticleState articleState;

    // 사용자들의 반응을 살피기 위한 일정 기간동안의 좋아요 수를 체크한다.
    // 등록일시 ~ 해지일시
    @Column(nullable = false)
    private LocalDateTime registeredDate; // 게시물 올린 일시

    private LocalDateTime UnregisteredDate; // 게시물 내린 일시

    // 생성메서드
    public static Article createNewArticle(String description) {
        return new Article(description);
    }

    private Article(String description) {
        this.registeredDate = LocalDateTime.now();
        this.like = 0;
        this.articleState = ArticleState.OPEN;
        this.description = description;
    }

    // 비즈니스 로직
    // 좋아요 +1
    public void plusLike() {
        this.like++;
    }

    public void resetLike() {
        this.like=0;
    }

    // 게시물 상태 변경
    public void changeState() {
        if (this.articleState==ArticleState.OPEN) {
            this.articleState=ArticleState.CLOSE;
        } else {
            this.articleState=ArticleState.OPEN;
        }
    }

    // 게시물 내릴때 일시 등록
    public void setUnregisteredDate() {
        this.UnregisteredDate = LocalDateTime.now();
    }


}
