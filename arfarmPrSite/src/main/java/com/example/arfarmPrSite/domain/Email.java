package com.example.arfarmPrSite.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter
@NoArgsConstructor
public class Email extends BaseTime{

    @Id @GeneratedValue
    @Column(name = "email_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    public static Email registeredEmail(String email) {
        return new Email(email);
    }

    private Email(String email) {
        this.email = email;
    }

}
