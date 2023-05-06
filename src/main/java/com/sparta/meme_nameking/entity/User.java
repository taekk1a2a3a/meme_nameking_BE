package com.sparta.meme_nameking.entity;

import com.sparta.meme_nameking.dto.SignupRequestDto;

import javax.persistence.*;

public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;


    public User(SignupRequestDto signupRequestDto) {

        this.username = signupRequestDto.getUsername();
        this.password = signupRequestDto.getPassword();

    }


}


