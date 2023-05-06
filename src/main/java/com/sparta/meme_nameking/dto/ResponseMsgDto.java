package com.sparta.meme_nameking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ResponseMsgDto<T> {

    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public static <T> ResponseEntity<ResponseMsgDto<T>> setSuccess(int status, String message, T data){
        ResponseMsgDto<T> responseMsgDto = new ResponseMsgDto<>(status, message, data);
        return new ResponseEntity<>(responseMsgDto, HttpStatus.valueOf(status));
    }

    public static <T> ResponseEntity<ResponseMsgDto<T>> setFail(int status, String message){
        ResponseMsgDto<T> responseMsgDto = new ResponseMsgDto<>(status, message, null);
        return new ResponseEntity<>(responseMsgDto, HttpStatus.valueOf(status));
    }
}
