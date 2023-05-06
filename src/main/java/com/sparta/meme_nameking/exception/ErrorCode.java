package com.sparta.meme_nameking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 400 BAD_REQUEST
    CANNOT_FOUND_USERNAME(BAD_REQUEST, "사용자가 존재하지 않습니다."),
    AUTHOR_NOT_SAME_MOD(BAD_REQUEST, "작성자만 수정할 수 있습니다."),
    AUTHOR_NOT_SAME_DEL(BAD_REQUEST, "작성자만 삭제할 수 있습니다."),
    INVALIDATED_TOKEN(BAD_REQUEST, "토큰이 유효하지 않습니다."),
    EXIST_USERNAME(BAD_REQUEST, "이미 존재하는 사용자 이름입니다."),
    EXIST_NICKNAME(BAD_REQUEST, "이미 존재하는 닉네임입니다."),

    // 404 NOT_FOUND
    USER_NOT_FOUND(NOT_FOUND, "회원을 찾을 수 없습니다."),
    POST_NOT_FOUND(NOT_FOUND, "해당 게시글을 찾을 수 없습니다."),
    REPLY_NOT_FOUND(NOT_FOUND, "해당 댓글을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String detail;
}