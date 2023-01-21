package com.example.arfarmPrSite.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor
@Table(name = "admin_comment")
public class AdminComment extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "admin_comment_id")
    private Long id;

    private String description;

    @OneToOne(mappedBy = "admin_comment")
    private Comment comment;

}
