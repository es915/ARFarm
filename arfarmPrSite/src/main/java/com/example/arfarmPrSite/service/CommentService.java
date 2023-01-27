package com.example.arfarmPrSite.service;

import com.example.arfarmPrSite.domain.*;
import com.example.arfarmPrSite.repository.AdminCommentRepository;
import com.example.arfarmPrSite.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.arfarmPrSite.domain.Comment.createComment;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AdminCommentRepository adminCommentRepository;

    // 댓글 등록하기
    @Transactional
    public Long commit(CommentDto commentDto) {
        CommentState state = checkComment(commentDto.getDescription());

        if (state == CommentState.HOLD) {
            // 비속어 의심 글 확인 -> 보류 조치 -> 메세지 발생
        }

        Comment comment = createComment(commentDto, state);
        commentRepository.save(comment);
        return comment.getId();
    }

    // 보류 댓글 풀기 - 관리자용
    @Transactional
    public Long stateToPass(Long id) {
        Comment comment = commentRepository.findById(id).get();
        comment.changeState(CommentState.PASS);
        return comment.getId();
    }

    // 댓글 보류 하기 - 관리자용
    @Transactional
    public Long stateToHold(Long id) {
        Comment comment = commentRepository.findById(id).get();
        comment.changeState(CommentState.HOLD);
        return comment.getId();
    }

    // 댓글 삭제 - 관리자용
    @Transactional
    public Long remove(Long id) {
        Comment comment = commentRepository.findById(id).get();
        commentRepository.delete(comment);
        return comment.getId();
    }

    // 댓글 전체 조회
    public List<Comment> comments() {
        return commentRepository.findAll();
    }

    // 보류 댓글 확인 조회
    public List<Comment> holdComments() {
        return commentRepository.findAllByCommentState(CommentState.HOLD);
    }

    // 관리자 댓글 조회
    public AdminComment viewAdminComment(Long id) {
        Comment comment = commentRepository.findById(id).get();
        return comment.getAdmin_comment();
    }

    private CommentState checkComment(String description) {

        if (description.indexOf("시발")>0) {
            return CommentState.HOLD;
        }
        return CommentState.PASS;
    }

}
