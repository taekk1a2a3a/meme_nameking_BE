package com.sparta.meme_nameking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private final int status; //http 상태 코드
    private final String message; //에러 메시지
}
