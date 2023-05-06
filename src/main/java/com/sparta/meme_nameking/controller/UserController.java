package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.SignupRequestDto;
import com.sparta.meme_nameking.dto.LoginRequestDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public ResponseMsgDto signup(@Valid SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseMsgDto login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }
}
