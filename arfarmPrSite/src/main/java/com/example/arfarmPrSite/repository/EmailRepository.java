package com.example.arfarmPrSite.repository;

import com.example.arfarmPrSite.domain.Comment;
import com.example.arfarmPrSite.domain.CommentState;
import com.example.arfarmPrSite.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmailRepository extends JpaRepository<Email, Long> {
    @Query("select e from Email e where e.email= :email")
    Optional<Email> findByEmail(String email);
}
