package com.flypiggyyoyoyo.im.authenticationservice.constants.user;


import lombok.Data;
import lombok.Getter;

@Getter
public enum ErrorEnum {
    SUCCESS(200,"OK"),
    REGISTER_ERROR(40001,"注册失败，用户已存在"),
    CODE_ERROR(40002,"验证码错误");

    private final int code;
    private final String message;

    private ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
