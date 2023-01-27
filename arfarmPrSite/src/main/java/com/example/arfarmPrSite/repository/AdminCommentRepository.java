package com.example.arfarmPrSite.repository;

import com.example.arfarmPrSite.domain.AdminComment;
import com.example.arfarmPrSite.domain.Comment;
import com.example.arfarmPrSite.domain.CommentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminCommentRepository extends  JpaRepository<AdminComment, Long> {
    @Query("select ac from AdminComment ac where ac.comment= :id")
    AdminComment findByCommentId(Long id);
}
