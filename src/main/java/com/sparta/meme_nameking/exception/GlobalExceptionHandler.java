package com.sparta.meme_nameking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    // CustomException을 처리하는 메서드
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {

        // 발생한 예외로부터 ErrorCode를 가져옴
        ErrorCode errorCode = e.getErrorCode();
        // ErrorCode에서 HttpStatus를 가져옴
        HttpStatus httpStatus = errorCode.getHttpStatus();
        // HttpStatus와 ErrorCode의 상세 메시지를 사용하여 ErrorResponse 객체를 생성하고,
        // ResponseEntity 객체를 반환함 (HTTP 상태 코드와 함께)
        return ResponseEntity.status(httpStatus)
                .body(new ErrorResponse(httpStatus.value(), errorCode.getDetail()));
    }

//    // CustomException 클래스에서 예외 핸들러
//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
//        return ErrorResponse.toResponseEntity(e.getErrorCode());
//    }
//
//    // Valid 예외 핸들러 (아이디 패스워드 유효성 검사)
//    @ExceptionHandler({BindException.class})
//    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
//        BindingResult bindingResult = e.getBindingResult();
//
//        StringBuilder sb = new StringBuilder();
//        for ( FieldError fieldError : bindingResult.getFieldErrors()) {
//            sb.append(fieldError.getDefaultMessage());
//            sb.append(", ");
//        }
//        return ErrorResponse.toResponseEntityValid(sb.toString(), HttpStatus.BAD_REQUEST);
//    }
}
