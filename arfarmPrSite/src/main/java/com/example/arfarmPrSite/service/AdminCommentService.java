package com.example.arfarmPrSite.service;

import com.example.arfarmPrSite.domain.AdminComment;
import com.example.arfarmPrSite.domain.AdminCommentDto;
import com.example.arfarmPrSite.domain.Comment;
import com.example.arfarmPrSite.repository.AdminCommentRepository;
import com.example.arfarmPrSite.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminCommentService {

    private final CommentRepository commentRepository;
    private final AdminCommentRepository adminCommentRepository;

    // 댓글 등록하기
    @Transactional
    public Long commit(AdminCommentDto adminCommentDto, Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        AdminComment adminComment = AdminComment.createComment(adminCommentDto, comment);
        adminCommentRepository.save(adminComment);
        return adminComment.getId();
    }

    // 댓글에 달린 관리자 댓글 조회하기
    public AdminComment findComment(Long commentId) {
        return adminCommentRepository.findByCommentId(commentId);
    }

    // 댓글 삭제
    @Transactional
    public Long removeAdminComment(Long id) {
        AdminComment comment = adminCommentRepository.findById(id).get();
        adminCommentRepository.delete(comment);
        return comment.getId();
    }


}
