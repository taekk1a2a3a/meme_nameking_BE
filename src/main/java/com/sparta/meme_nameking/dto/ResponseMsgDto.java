package com.sparta.meme_nameking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseMsgDto<T, U> {

    private int status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private U data2;

    public static <T> ResponseMsgDto<T, ?> setSuccess(int status, String message, T data){
        return new ResponseMsgDto<>(status, message, data, null);
    }

    public static <T, U> ResponseMsgDto<T, U> setSuccess(int status, String message, T data, U data2){
        return new ResponseMsgDto<>(status, message, data, data2);
    }

    public static <T> ResponseMsgDto<T, ?> setFail(int status, String message){
        return new ResponseMsgDto<>(status, message, null, null);
    }
}
