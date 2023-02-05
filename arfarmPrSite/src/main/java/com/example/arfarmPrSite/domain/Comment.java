package com.example.arfarmPrSite.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity @Getter
@NoArgsConstructor
public class Comment extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;


    private String name;

    private String phoneNumber;

    private String email;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private CommentState state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="admin_comment_id")
    private AdminComment admin_comment;

    // 생성 메서드
    public static Comment createComment(CommentDto commentDto, CommentState state) {
        if (commentDto.getEmailOrPhone().indexOf("@")!=-1) {
            return new Comment(commentDto.getName(), commentDto.getEmailOrPhone(), null, commentDto.getDescription(), state);
        } else {
            return new Comment(commentDto.getName(), null, commentDto.getEmailOrPhone(), commentDto.getDescription(), state);
        }
    }

    public void changeState(CommentState state) {
        if (state == CommentState.PASS) {
            this.state = CommentState.PASS;
        } else if (state == CommentState.HOLD) {
            this.state = CommentState.HOLD;
        }
    }

    private Comment(String name, String email, String phoneNumber, String description, CommentState state) {
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.description = description;
            this.state = state;
    }

}
