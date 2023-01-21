package com.example.arfarmPrSite.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter
@NoArgsConstructor
public class Article extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    private int like;

    private String title;

    public void plusLike() {
        this.like++;
    }

}
