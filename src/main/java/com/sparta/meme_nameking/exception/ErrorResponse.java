package com.sparta.meme_nameking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse {
    private final int status; //http 상태 코드
    private final String message; //에러 메시지

//    // 에러 반환 형식
//    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
//        return ResponseEntity
//                .status(errorCode.getHttpStatus())
//                .body(ErrorResponse.builder()
//                        .message(errorCode.getDetail())
//                        .status(errorCode.getHttpStatus().value())
//                        .build()
//                );
//    }
//
//    // 에러 반환 형식
//    public static ResponseEntity<ErrorResponse> toResponseEntityValid(String errorCode, HttpStatus httpStatus) {
//        return ResponseEntity
//                .status(httpStatus.value())
//                .body(ErrorResponse.builder()
//                        .message(errorCode)
//                        .status(httpStatus.value())
//                        .build()
//                );
//    }
}