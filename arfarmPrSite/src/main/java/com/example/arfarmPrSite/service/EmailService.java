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
    public Long create(EmailDto emailDto) {
        if (duplicateCheck(emailDto.getEmail())) {
            return emailRepository.findByEmail(emailDto.getEmail()).get().getId();
        }
        Email email = Email.registeredEmail(emailDto.getEmail());
        emailRepository.save(email);
        return email.getId();
    }

    private boolean duplicateCheck(String email) {
        try {
            emailRepository.findByEmail(email);
        } catch (NullPointerException e) {
            return false; // 중복이 안될경우
        }
        return true; // 중복이 된 경우
    }

}
