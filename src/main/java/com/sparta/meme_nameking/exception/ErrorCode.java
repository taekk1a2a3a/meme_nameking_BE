package com.sparta.meme_nameking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALIDATED_TOKEN(BAD_REQUEST, "토큰이 유효하지 않습니다."),

    EXIST_USERNAME(BAD_REQUEST, "이미 존재하는 사용자 이름입니다."),
    EXIST_NICKNAME(BAD_REQUEST, "이미 존재하는 닉네임입니다.");


    private final HttpStatus httpStatus;
    private final String detail;
}