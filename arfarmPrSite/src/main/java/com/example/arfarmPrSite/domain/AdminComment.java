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

    @OneToOne(mappedBy = "admin_comment", orphanRemoval = true)
    private Comment comment;

    public static AdminComment createComment(AdminCommentDto adminCommentDto, Comment comment) {
        return new AdminComment(adminCommentDto.getDescription(), comment);
    }

    private AdminComment(String description, Comment comment) {
        this.description = description;
        this.comment = comment;
    }

}
