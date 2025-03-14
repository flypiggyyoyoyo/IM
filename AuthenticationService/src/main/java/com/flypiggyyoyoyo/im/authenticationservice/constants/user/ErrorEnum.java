package com.flypiggyyoyoyo.im.authenticationservice.constants.user;


import lombok.Data;
import lombok.Getter;

@Getter
public enum ErrorEnum {
    SUCCESS(200,"OK"),
    REGISTER_ERROR(40001,"注册失败，用户已存在"),
    CODE_ERROR(40002,"验证码错误"),
    Login_ERROR(40003,"登录失败，用户名或密码错误"),
    NO_USER_ERROR(40004,"用户不存在"),

    SIGNATURE_VERIFICATION_FAILED(40101, "签名验证失败"),
    ILLEGAL_SOURCE(40301, "非法请求来源"),

    UPDATE_AVATAR_ERROR(50011,"更新头像失败");

    private final int code;
    private final String message;

    private ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
