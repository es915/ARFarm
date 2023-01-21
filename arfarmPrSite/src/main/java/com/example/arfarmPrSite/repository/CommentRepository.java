package com.example.arfarmPrSite.repository;


import com.example.arfarmPrSite.domain.Comment;
import com.example.arfarmPrSite.domain.CommentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.state= :state")
    List<Comment> findAllByCommentState(CommentState state);

}
