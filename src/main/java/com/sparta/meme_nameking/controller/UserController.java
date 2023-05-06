
package com.sparta.meme_nameking.controller;

import com.sparta.meme_nameking.dto.SignupRequestDto;
import com.sparta.meme_nameking.dto.UserRequestDto;
import com.sparta.meme_nameking.dto.ResponseMsgDto;
import com.sparta.meme_nameking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<ResponseMsgDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        return userService.signup(signupRequestDto);
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseMsgDto> login(@RequestBody UserRequestDto userRequestDto, HttpServletResponse response){
        return userService.login(userRequestDto, response);
    }
}
