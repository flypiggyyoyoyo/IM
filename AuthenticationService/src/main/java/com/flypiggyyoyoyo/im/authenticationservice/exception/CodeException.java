package com.flypiggyyoyoyo.im.authenticationservice.exception;

import com.flypiggyyoyoyo.im.authenticationservice.constants.user.ErrorEnum;

public class CodeException extends RuntimeException{
    private final int code;

    public CodeException(String message) {
        super(message);

        this.code = ErrorEnum.SUCCESS.getCode();
    }

    public CodeException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());

        this.code = errorEnum.getCode();
    }

    public CodeException(ErrorEnum errorEnum, String message) {
        super(message);
        this.code = errorEnum.getCode();
    }

    public int getCode() {
        return this.code;
    }
}
