package com.example.arfarmPrSite.service;

import com.example.arfarmPrSite.domain.Email;
import com.example.arfarmPrSite.domain.EmailDto;
import com.example.arfarmPrSite.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmailService {

    private final EmailRepository emailRepository;

    @Transactional
    public Email create(EmailDto emailDto) {
        duplicateCheck(emailDto.getEmail());
        return emailRepository.save(Email.registeredEmail(emailDto.getEmail()));
    }

    private void duplicateCheck(String email) {
        try {
            emailRepository.findByEmail(email);
        } catch (NullPointerException e) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

}
