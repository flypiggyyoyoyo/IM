package com.flypiggyyoyoyo.im.authenticationservice.common;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
public class Result<T> {

    private int code;

    private String msg;

    private T data;

    public static <T> Result<T> OK(T data) {
        Result<T> result = new Result<>();
        return result.setCode(HttpStatus.OK.value())
                .setData(data);
    }

    public static <T> Result<T> DatabaseError(String msg) {
        Result<T> result = new Result<>();
        return result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setMsg(msg);
    }
}
